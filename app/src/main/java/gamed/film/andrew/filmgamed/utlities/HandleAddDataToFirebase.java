package gamed.film.andrew.filmgamed.utlities;

import android.app.Dialog;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

import developer.mokadim.projectmate.dialog.IndicatorStyle;
import developer.mokadim.projectmate.dialog.ProgressDialog;
import gamed.film.andrew.filmgamed.R;
import gamed.film.andrew.filmgamed.interfaces.InterfaceAddDataToFirebase;


/**
 * Created by lenovo on 6/28/2017.
 */

public class HandleAddDataToFirebase
{
    private static Context context;
    private static HandleAddDataToFirebase instance = null;
    private InterfaceAddDataToFirebase clickListener;
    private static DatabaseReference myRef;

    public static HandleAddDataToFirebase getInstance(Context context)
    {

        HandleAddDataToFirebase.context = context;

        if (instance == null)
        {
            instance = new HandleAddDataToFirebase();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            myRef = database.getReference();
            myRef.keepSynced(true);

        }
        return instance;
    }

    public void setClickDialogListener(InterfaceAddDataToFirebase itemClickListener)
    {
        this.clickListener = itemClickListener;
    }

  /*  public void callAddProfileData(final String flag, ModelProfile modelMember)
    {
        final Dialog progressDialog = new ProgressDialog(context, IndicatorStyle.BallBeat).show();
        progressDialog.show();

        if (HelpMe.getInstance(context).isOnline())
        {

            DatabaseReference myRefJobs = myRef.child("users")
                    .child(FirebaseAuth.getInstance().getUid())
                    .child("details");

            myRefJobs.setValue(modelMember, new DatabaseReference.CompletionListener()
            {
                public void onComplete(DatabaseError error, DatabaseReference ref)
                {

                    if (error == null)
                    {
                        clickListener.onDataAddedSuccess(flag);
                    } else
                    {
                        clickListener.onDataAddedFailed(flag);
                    }

                    progressDialog.dismiss();
                }
            });
        } else
        {
            TastyToast.makeText(context, context.getString(R.string.connection_error), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            progressDialog.dismiss();
        }

    }
*/

}
