package com.example.onlineclothingstore.item_and_category_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.onlineclothingstore.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayCategory extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    Button btnAdd;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    FloatingActionButton floatingActionButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_category);

        //Main Menu
        toolbar = findViewById(R.id.toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);

        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);



        // Add Category

        floatingActionButton1 = (FloatingActionButton)findViewById(R.id.floatingbtn1);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisplayCategory.this,AddItem.class));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CategoryModule>  options=
                new FirebaseRecyclerOptions.Builder<CategoryModule>()
                  .setQuery(FirebaseDatabase.getInstance().getReference().child("Categories"),CategoryModule.class)
                  .build();

        categoryAdapter = new CategoryAdapter(options);
        recyclerView.setAdapter(categoryAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        categoryAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        categoryAdapter.stopListening();
    }



    //to  stop app get close when pressing back key
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

     //Menu Item select
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_category:
                Intent int1 =new Intent(DisplayCategory.this,AddCategory.class);
                startActivity(int1);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


//
//    //search
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.search,menu);
//
//        MenuItem item= menu.findItem(R.id.search);
//        SearchView searchView =(SearchView)item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                txtSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                txtSearch(newText);
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
//
//        FirebaseRecyclerOptions<CategoryModule>  options=
//                new FirebaseRecyclerOptions.Builder<CategoryModule>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Categories").orderByChild("category").startAt(str).endAt(str+"~"),CategoryModule.class)
//                        .build();
//
//        categoryAdapter = new CategoryAdapter(options);
//        categoryAdapter.startListening();
//        recyclerView.setAdapter(categoryAdapter);
//    }
}