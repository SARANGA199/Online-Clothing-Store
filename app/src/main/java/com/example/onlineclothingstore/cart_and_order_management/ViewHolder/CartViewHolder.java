package com.example.onlineclothingstore.cart_and_order_management.ViewHolder;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineclothingstore.R;
import com.example.onlineclothingstore.cart_and_order_management.ItemClickListner;
import com.google.android.material.imageview.ShapeableImageView;


public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String TAG = "TAG";
    public TextView txtProductName, txtProductPrice, txtProductQuantity;
    public ShapeableImageView shapeableImageView;
   // public ImageView imageView;

    private ItemClickListner itemClickListner;

    public CartViewHolder(View itemView) {
        super(itemView);

        txtProductName = itemView.findViewById(R.id.cart_product_name);
        txtProductPrice = itemView.findViewById(R.id.cart_product_price);
        txtProductQuantity = itemView.findViewById(R.id.cart_product_quantity);
        //imageView = itemView.findViewById(R.id.cart_product_image);
        shapeableImageView = (ShapeableImageView)itemView.findViewById(R.id.img3);

        Log.d(TAG, "CartViewHolder: "+txtProductName+""+txtProductPrice+""+txtProductQuantity+"");

    }

    public void onClick(View view){

        itemClickListner.onClick(view,getAdapterPosition(), false);
    }

    public void setItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListner = itemClickListner;
    }
}
