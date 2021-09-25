package com.example.onlineclothingstore.user_and_payment_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.onlineclothingstore.MainActivity;
import com.example.onlineclothingstore.R;
import com.example.onlineclothingstore.item_and_category_management.DisplayItemsUser;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    private Button logout,profile,addcards,allcards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile = (Button) findViewById(R.id.myprof);
        profile.setOnClickListener(this);

        addcards = (Button) findViewById(R.id.cardsadd);
        addcards.setOnClickListener(this);

        allcards = (Button) findViewById(R.id.allcards);
        allcards.setOnClickListener(this);

        logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.myprof:
                startActivity(new Intent(this, DisplayItemsUser.class));
                break;
            case R.id.cardsadd:
                startActivity(new Intent(this, cardform.class));
                break;
            case R.id.allcards:
                startActivity(new Intent( this,Addedcards.class));
                break;
        }
    }
}