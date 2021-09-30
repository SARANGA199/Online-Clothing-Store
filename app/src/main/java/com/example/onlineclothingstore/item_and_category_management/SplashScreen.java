package com.example.onlineclothingstore.item_and_category_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineclothingstore.MainActivity;
import com.example.onlineclothingstore.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH = 6000;

    Animation topAnim,bottomAnim;
    ImageView image;
    TextView topic,des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.splash);
        topic = findViewById(R.id.topic1);
        des = findViewById(R.id.topic2);


        image.setAnimation(topAnim);
        topic.setAnimation(bottomAnim);
        des.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH);

    }
}