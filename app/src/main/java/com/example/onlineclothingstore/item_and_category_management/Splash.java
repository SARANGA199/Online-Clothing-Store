package com.example.onlineclothingstore.item_and_category_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.onlineclothingstore.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //start main screen after 2seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(Splash.this,Home.class));
                finish();//finish activity

            }
        },2000);
    }
}