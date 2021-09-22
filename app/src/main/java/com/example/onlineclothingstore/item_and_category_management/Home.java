
package com.example.onlineclothingstore.item_and_category_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.onlineclothingstore.R;
import com.example.onlineclothingstore.databinding.ActivityHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    //view binding
    private ActivityHomeBinding binding;


    //arraylist to store
    private ArrayList<ModelCategory> categoryArrayList;

    //adapter
    private AdapterCategory adapterCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadCategories();
    }

    private void loadCategories() {

        //init arraylist
        categoryArrayList = new ArrayList<>();

        //get all categories
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Categories");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //clear arraylist before adding data into it
                categoryArrayList.clear();

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    //get data
                    ModelCategory model = dataSnapshot.getValue(ModelCategory.class);

                    //add data to arraylist
                    categoryArrayList.add(model);
                }

                adapterCategory = new AdapterCategory(Home.this,categoryArrayList);
                //set adapter to recycleview
                binding.categories1.setAdapter(adapterCategory);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}