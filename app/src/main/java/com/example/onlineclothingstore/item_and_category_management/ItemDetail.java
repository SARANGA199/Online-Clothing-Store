package com.example.onlineclothingstore.item_and_category_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.onlineclothingstore.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ItemDetail extends AppCompatActivity {

    ShapeableImageView shapeableImageView;
    TextView txt1,txt2,txt3,txt4;
    Button btn;

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_item_detail);

        shapeableImageView = (ShapeableImageView)findViewById(R.id.img3);
        txt1 = (TextView)findViewById(R.id.nameText3);
        txt2 = (TextView)findViewById(R.id.price3);
        txt3 = (TextView)findViewById(R.id.discountPrice3);
        txt4 = (TextView)findViewById(R.id.descri);

        ref = FirebaseDatabase.getInstance().getReference().child("Products");

        btn = (Button)findViewById(R.id.btnDelete3);

        String itemkey = getIntent().getStringExtra("itemKey");

        ref.child(itemkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                  if(snapshot.exists()){
                      String itemName = snapshot.child("itemName").getValue().toString();
                      String itemprice = snapshot.child("iprice").getValue().toString();
                      String itemdis = snapshot.child("idiscountPrice").getValue().toString();
                      String itemimage = snapshot.child("image").getValue().toString();
                      String itemdes = snapshot.child("idescription").getValue().toString();

                      Picasso.get().load(itemimage).into(shapeableImageView);
                      txt1.setText(itemName);
                      txt2.setText(itemprice);
                      txt3.setText(itemdis);
                      txt4.setText(itemdes);

                      //Navigation to main
                      btn.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              Intent int1 = new Intent(ItemDetail.this,DisplayCategory.class);
                              startActivity(int1);
                          }
                      });
                  }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}