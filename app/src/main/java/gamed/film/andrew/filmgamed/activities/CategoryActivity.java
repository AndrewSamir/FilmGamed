package gamed.film.andrew.filmgamed.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gamed.film.andrew.filmgamed.R;
import gamed.film.andrew.filmgamed.adapters.AdapterCategories;
import gamed.film.andrew.filmgamed.interfaces.InterfaceGetDataFromFirebase;
import gamed.film.andrew.filmgamed.models.ModelCategoryDetails;
import gamed.film.andrew.filmgamed.utlities.HandleGetDataFromFirebase;

public class CategoryActivity extends AppCompatActivity implements InterfaceGetDataFromFirebase
{


    AdapterCategories adapterCategories;
    List<ModelCategoryDetails> modelCategoryDetailsList;

    RecyclerView rvCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        rvCategories = findViewById(R.id.rvCategories);

        rvCategories.setLayoutManager(new GridLayoutManager(this,2));
        HandleGetDataFromFirebase.getInstance(this).setGetDataFromFirebaseInterface(this);
        HandleGetDataFromFirebase.getInstance(this).callGetAllCategories(getString(R.string.flag_call_getAll_categories));

    }

    @Override
    public void onGetDataFromFirebase(DataSnapshot dataSnapshot, String flag)
    {
        if (flag.equals(getString(R.string.flag_call_getAll_categories)))
        {
            modelCategoryDetailsList = new ArrayList<>();
            for (DataSnapshot child : dataSnapshot.getChildren())
            {
                ModelCategoryDetails modelCategoryDetails = child.child(getString(R.string.firebae_categoryDetails)).getValue(ModelCategoryDetails.class);
                modelCategoryDetailsList.add(modelCategoryDetails);
            }
            adapterCategories = new AdapterCategories(modelCategoryDetailsList, this);
            rvCategories.setAdapter(adapterCategories);
        }
    }


}
