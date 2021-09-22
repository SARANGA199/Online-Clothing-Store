package com.example.onlineclothingstore.user_and_payment_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.onlineclothingstore.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Addedcards extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    DatabaseReference database;
    CardAdapter cardAdapter;
    ArrayList<Cards> list;

    private Button addcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addedcards);

        recyclerView = findViewById(R.id.cardlist);
        database = FirebaseDatabase.getInstance().getReference("paymentCards");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        cardAdapter = new CardAdapter(this, list);
        recyclerView.setAdapter(cardAdapter);

        addcard = (Button) findViewById(R.id.addcrd);
        addcard.setOnClickListener((View.OnClickListener) this);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Cards cards = dataSnapshot.getValue(Cards.class);
                    list.add(cards);

                }
                cardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addcrd:
                startActivity(new Intent(this, cardform.class));
                break;
        }
    }
}