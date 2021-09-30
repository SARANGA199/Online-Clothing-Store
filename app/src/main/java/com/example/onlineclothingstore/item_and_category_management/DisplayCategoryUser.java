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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SearchView;

import com.example.onlineclothingstore.MainActivity;
import com.example.onlineclothingstore.R;
import com.example.onlineclothingstore.cart_and_order_management.CartActivity;
import com.example.onlineclothingstore.cart_and_order_management.UserProductsActivity;
import com.example.onlineclothingstore.request_and_review_management.FirstActivity;
import com.example.onlineclothingstore.user_and_payment_management.UserProfile;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class DisplayCategoryUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    RecyclerView recyclerView;
    CategoryUserAdapter categoryAdapter;
    Button btnAdd;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    FloatingActionButton floatingActionButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_category_user);


       // setContentView(R.layout.activity_display_category);

        //Main Menu
        toolbar = findViewById(R.id.toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);

        //Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //look
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


        // Add Category

        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.floatingbtn2);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisplayCategoryUser.this, CartActivity.class));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rv5);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CategoryModule> options =
                new FirebaseRecyclerOptions.Builder<CategoryModule>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Categories"), CategoryModule.class)
                        .build();

        categoryAdapter = new CategoryUserAdapter(options);
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
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent int1 = new Intent(DisplayCategoryUser.this, DisplayCategoryUser.class);
            startActivity(int1);
        }
    }

    //Menu Item select
    //@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_req:
                Intent int5 = new Intent(DisplayCategoryUser.this, FirstActivity.class);
                startActivity(int5);
                break;
            case R.id.nav_order:
                Intent int6 = new Intent(DisplayCategoryUser.this, UserProductsActivity.class);
                startActivity(int6);
                break;
            case R.id.nav_category:
                Intent int1 = new Intent(DisplayCategoryUser.this, DisplayItemsUser.class);
                startActivity(int1);
                break;
            case R.id.nav_profile:
                Intent int2 = new Intent(DisplayCategoryUser.this, UserProfile.class);
                startActivity(int2);
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(DisplayCategoryUser.this, MainActivity.class));
                break;
            case R.id.nav_contact:
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}