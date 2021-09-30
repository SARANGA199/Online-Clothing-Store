package com.example.onlineclothingstore.user_and_payment_management;

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
import android.widget.Button;
import android.widget.SearchView;

import com.example.onlineclothingstore.R;
import com.example.onlineclothingstore.item_and_category_management.DisplayItemsUser;
import com.example.onlineclothingstore.item_and_category_management.ItemDetail;
import com.example.onlineclothingstore.user_and_payment_management.Cards;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class Addedcards extends AppCompatActivity{

    RecyclerView recyclerView;
    CardAdapter cardAdapter;

    Button addcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_addedcards);

        recyclerView = (RecyclerView) findViewById(R.id.cardlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Cards> options =
                new FirebaseRecyclerOptions.Builder<Cards>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("paymentCards"), Cards.class)
                        .build();

        cardAdapter = new CardAdapter(options);
        recyclerView.setAdapter(cardAdapter);

        addcard = (Button) findViewById(R.id.addcrd);
        addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), cardform.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        cardAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cardAdapter.stopListening();
    }

    //to  stop app get close when pressing back key
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent int1 = new Intent(Addedcards.this, DisplayItemsUser.class);
        startActivity(int1);
    }

}