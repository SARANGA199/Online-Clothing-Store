package com.example.onlineclothingstore.item_and_category_management;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;

import com.example.onlineclothingstore.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;



public class DisplayItemsUser extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      requestWindowFeature(Window.FEATURE_NO_TITLE);
      this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // getSupportActionBar().hide();

        setContentView(R.layout.activity_display_items_user);

        recyclerView = (RecyclerView)findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ItemModel> options =
                new FirebaseRecyclerOptions.Builder<ItemModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products"), ItemModel.class)
                        .build();

        itemAdapter = new ItemAdapter(options);
        recyclerView.setAdapter(itemAdapter);


}

    @Override
    protected void onStart() {
        super.onStart();
        itemAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        itemAdapter.stopListening();
    }

    //SEARCH
    //  @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.search,menu);
//
//        MenuItem item = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView)item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                txtSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                txtSearch(query);
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//
//    private void txtSearch(String str){
//
//        FirebaseRecyclerOptions<ItemModel> options =
//                new FirebaseRecyclerOptions.Builder<ItemModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products").orderByChild("iproduct").startAt(str).endAt(str+"~"), ItemModel.class)
//                        .build();
//
//
//        itemAdapter = new ItemAdapter(options);
//        itemAdapter.startListening();
//        recyclerView.setAdapter(itemAdapter);
//    }
}

