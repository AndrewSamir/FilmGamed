package gamed.film.andrew.filmgamed.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import gamed.film.andrew.filmgamed.R;
import gamed.film.andrew.filmgamed.adapters.AdapterCategories;
import gamed.film.andrew.filmgamed.adapters.AdapterCategoryItems;
import gamed.film.andrew.filmgamed.interfaces.InterfaceGetDataFromFirebase;
import gamed.film.andrew.filmgamed.models.ModelCategoryDetails;
import gamed.film.andrew.filmgamed.models.ModelItem;
import gamed.film.andrew.filmgamed.singleton.SingletonData;
import gamed.film.andrew.filmgamed.utlities.HandleGetDataFromFirebase;

public class CategoryItemsActivity extends AppCompatActivity implements InterfaceGetDataFromFirebase {
    AdapterCategoryItems adapterCategoryItems;
    List<ModelItem> modelItemList;

    RecyclerView rvCategoryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvCategoryItems = findViewById(R.id.rvCategoryItems);
        rvCategoryItems.setLayoutManager(new GridLayoutManager(this, 2));


        HandleGetDataFromFirebase.getInstance(this).setGetDataFromFirebaseInterface(this);
        HandleGetDataFromFirebase.getInstance(this).callGetAllCategoryItems("testFlag", SingletonData.getInstance().getCategoryName());
    }

    @Override
    public void onGetDataFromFirebase(DataSnapshot dataSnapshot, String flag) {

        modelItemList = new ArrayList<>();
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            ModelItem modelItem = child.getValue(ModelItem.class);
            modelItemList.add(modelItem);
        }
        adapterCategoryItems = new AdapterCategoryItems(modelItemList, this);
        rvCategoryItems.setAdapter(adapterCategoryItems);

    }
}
