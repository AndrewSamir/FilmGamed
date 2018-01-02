package gamed.film.andrew.filmgamed.utlities;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

import developer.mokadim.projectmate.dialog.IndicatorStyle;
import developer.mokadim.projectmate.dialog.ProgressDialog;
import gamed.film.andrew.filmgamed.R;
import gamed.film.andrew.filmgamed.interfaces.InterfaceDailogClicked;
import gamed.film.andrew.filmgamed.interfaces.InterfaceGetDataFromFirebase;


/**
 * Created by lenovo on 6/28/2017.
 */

public class HandleGetDataFromFirebase
{
    private static Context context;
    private static HandleGetDataFromFirebase instance = null;
    private InterfaceGetDataFromFirebase clickListener;
    private InterfaceDailogClicked interfaceDailogClicked;
    private static FirebaseDatabase database;
    private static DatabaseReference myRef;

    public static HandleGetDataFromFirebase getInstance(Context context)
    {

        HandleGetDataFromFirebase.context = context;

        if (instance == null)
        {
            instance = new HandleGetDataFromFirebase();
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference();
            myRef.keepSynced(true);

        }
        return instance;
    }

    public void setGetDataFromFirebaseInterface(InterfaceGetDataFromFirebase itemClickListener)
    {
        this.clickListener = itemClickListener;
    }

    public void setListnerToDialog(InterfaceDailogClicked interfaceDailogClicked)
    {
        this.interfaceDailogClicked = interfaceDailogClicked;
    }

    public void callGetAllCategories(final String flag)
    {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallZigZag).show();
        progressDialog.show();
        if (isOnline())
        {
            DatabaseReference myRefJobs = myRef.child(context.getString(R.string.firebase_categories));
            myRefJobs.addListenerForSingleValueEvent(new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    clickListener.onGetDataFromFirebase(dataSnapshot, flag);
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError error)
                {
                    TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                    progressDialog.dismiss();
                }
            });
        } else
        {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            progressDialog.dismiss();
        }
    }

    private void populate(ArrayList<String> jobItems, String flag, String dialogTitle, String serviseId, int chairsInRow)
    {

        if (jobItems.size() > 0)
        {
            ShowDIalog(jobItems, flag, dialogTitle, serviseId, chairsInRow);
        }
    }

    private void ShowDIalog(ArrayList<String> arrName, final String flag, final String dialogTitle, final String serviseId, final int chairsInRow)
    {
        new MaterialDialog.Builder(context)
                .title(dialogTitle)
                .items(arrName)

                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice()
                {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text)
                    {
                        if (interfaceDailogClicked != null)
                            interfaceDailogClicked.onClickDialog(text + "", flag, dialogTitle, serviseId, chairsInRow);
                        //Log.e("loge", text + "" + which);
                        dialog.dismiss();
                        return true;
                    }
                })
                .negativeText(context.getString(R.string.cancel))
                .show();
    }


    private Boolean isOnline()
    {
        try
        {
            Process p1 = Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            return (returnVal == 0);
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }


}
