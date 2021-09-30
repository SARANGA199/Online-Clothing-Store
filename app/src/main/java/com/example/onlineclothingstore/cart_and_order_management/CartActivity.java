package com.example.onlineclothingstore.cart_and_order_management;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlineclothingstore.R;
import com.example.onlineclothingstore.cart_and_order_management.Model.Cart;
import com.example.onlineclothingstore.cart_and_order_management.Prevalent.Prevalent;
import com.example.onlineclothingstore.cart_and_order_management.ViewHolder.CartViewHolder;
import com.example.onlineclothingstore.item_and_category_management.DisplayCategoryUser;
import com.example.onlineclothingstore.item_and_category_management.DisplayItemsUser;
import com.example.onlineclothingstore.item_and_category_management.ItemDetail;
import com.example.onlineclothingstore.user_and_payment_management.Addedcards;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class CartActivity extends AppCompatActivity {

    private ShapeableImageView shapeableImageView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button Nextprocessbtn,btnnext;
    private TextView textTotalAmount;
    private static final String TAG = "TAG";
    private int overTotalPrice=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Nextprocessbtn = (Button) findViewById(R.id.next_process_btn);
        btnnext = (Button)findViewById(R.id.btn);
        //shapeableImageView = (ShapeableImageView)findViewById(R.id.img3);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this,ConfirmFinalOrderActivity.class);
                intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                startActivity(intent);
                finish();
            }
        });


    }

    protected void onStart() {


        super.onStart();



        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cart> options =
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(cartListRef.child("User View")
                       .child(FirebaseAuth.getInstance().getUid())
                                .child("Products"), Cart.class)
                               // .child("09001168601438").child("Products"), Cart.class)
                        .build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter
                = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
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

                int oneTypeProductPrice = ((Integer.valueOf(model.getPrice()))) * Integer.valueOf(model.getQuantity());
                overTotalPrice = overTotalPrice + oneTypeProductPrice;



                Log.d(TAG, "onBindViewHolder: "+holder.txtProductName+""+holder.txtProductPrice+""+holder.txtProductPrice+"");

                //Log.d(TAG, "CartViewHolder: "+txtProductName+""+txtProductPrice+""+txtProductQuantity+"");

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence options[] = new CharSequence[]
                                {
                                        "Edit",
                                        "Remove"
                                };
                        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                        builder.setTitle("Card Option:");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if(i == 0){
                                    Intent intent = new Intent(CartActivity.this, ItemDetail.class);
                                    intent.putExtra("itemKey",getRef(position).getKey());
                                    //intent.putExtra("qty",model.getQuantity());
                                    startActivity(intent);
                                }
                                if(i== 1){
                                    cartListRef.child("User View")
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .child("Products")
                                            .child(model.getPid())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                    if(task.isSuccessful())
                                                    {
                                                        Toast.makeText(CartActivity.this, "Item removed successfully", Toast.LENGTH_SHORT).show();
/****************************   redirect after delete  ***************************************************************************************/
                                                        Intent intent = new Intent(CartActivity.this, DisplayItemsUser.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                }

                            }
                        });

                        builder.show();
                    }
                });

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

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    //to  stop app get close when pressing back key
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent int1 = new Intent(CartActivity.this, DisplayCategoryUser.class);
        startActivity(int1);
    }

    //comment
}