package com.example.onlineclothingstore.item_and_category_management;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.onlineclothingstore.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductDetailsActivity extends AppCompatActivity {

    private FloatingActionButton addToCartBtn;
    private ElegantNumberButton numberButton;
    private TextView productprice, productdescription, productname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        addToCartBtn = (FloatingActionButton) findViewById(R.id.add_product_to_cart_btn);
        numberButton = (ElegantNumberButton)findViewById(R.id.number_btn);
        productname = (TextView)findViewById(R.id.product_name_details);
        productdescription = (TextView)findViewById(R.id.product_description_details);
        productprice = (TextView)findViewById(R.id.product_price_details);
    }
}