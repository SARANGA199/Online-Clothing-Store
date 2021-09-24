package com.example.onlineclothingstore.item_and_category_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.onlineclothingstore.R;
import com.example.onlineclothingstore.cart_and_order_management.CartActivity;
import com.example.onlineclothingstore.cart_and_order_management.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ProductDetailsActivity extends AppCompatActivity {

    private Button addToCartBtn;
    private ElegantNumberButton numberButton;
    private TextView productprice, productdescription, productname;
    private  String productID= "001";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productID = getIntent().getStringExtra("pid");

        addToCartBtn = (Button) findViewById(R.id.add_product_to_cart_btn);
        numberButton = (ElegantNumberButton)findViewById(R.id.number_btn);
        productname = (TextView)findViewById(R.id.product_name_details);
        productdescription = (TextView)findViewById(R.id.product_description_details);
        productprice = (TextView)findViewById(R.id.product_price_details);


        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingtoToCartList();
            }
        });

    }

    private void addingtoToCartList() {

        String SaveCurrentTime,SaveCurrentDate;

           // Calendar calForDate = new Calendar.getInstance();
          //  SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
            //SaveCurrentDate = currentDate.format(calForDate.getTime());

       // SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
       // SaveCurrentTime = currentTime.format(calForDate.getTime());

        DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("pid",productID);
        cartMap.put("pname",productname.getText().toString());
        cartMap.put("price",productprice.getText().toString());
        //cartMap.put("date",SaveCurrentDate);
        //cartMap.put("time",SaveCurrentTime);
        cartMap.put("quantity",numberButton.getNumber());
        cartMap.put("discount","");

        cartListRef.child("Admin View").child(Prevalent.currentOnlineUser.getPhone())
                .child("Products").child(productID)
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(ProductDetailsActivity.this, "Added to Cart List.",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
                            startActivity(intent);
                        }
                    }
                });

    }
}