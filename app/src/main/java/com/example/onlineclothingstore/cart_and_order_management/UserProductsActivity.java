package com.example.onlineclothingstore.cart_and_order_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.onlineclothingstore.R;
import com.example.onlineclothingstore.cart_and_order_management.Model.Cart;
import com.example.onlineclothingstore.cart_and_order_management.ViewHolder.CartViewHolder;
import com.example.onlineclothingstore.item_and_category_management.DisplayCategoryUser;
import com.example.onlineclothingstore.user_and_payment_management.Addedcards;
import com.example.onlineclothingstore.user_and_payment_management.cardform;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class UserProductsActivity extends AppCompatActivity {

    private RecyclerView myproductsList;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference cartListRef;

    //private String userID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_products);

        //userID = getIntent().getStringExtra("uid");

        myproductsList = findViewById(R.id.myproducts_list);
        myproductsList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        myproductsList.setLayoutManager(layoutManager);

        cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List")
                .child("Admin View").child(FirebaseAuth.getInstance().getUid()).child("Products");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Cart> options=
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(cartListRef, Cart.class)
                        .build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull CartViewHolder holder, int position, @NonNull @NotNull Cart model) {

                holder.txtProductQuantity.setText("Qty"+model.getQuantity());
                holder.txtProductPrice.setText("Rs."+model.getPrice());
                holder.txtProductName.setText(model.getPname());

                Glide.with(holder.shapeableImageView.getContext())
                        .load(model.getImage())
                        .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                        .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                        .into(holder.shapeableImageView);
            }

            @NonNull
            @NotNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent,false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };

        myproductsList.setAdapter(adapter);
        adapter.startListening();
    }

    //to  stop app get close when pressing back key
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent int1 = new Intent(UserProductsActivity.this, DisplayCategoryUser.class);
        startActivity(int1);
    }
}