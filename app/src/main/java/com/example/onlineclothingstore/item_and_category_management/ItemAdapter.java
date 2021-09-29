package com.example.onlineclothingstore.item_and_category_management;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlineclothingstore.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.imageview.ShapeableImageView;


import org.jetbrains.annotations.NotNull;


public class ItemAdapter extends FirebaseRecyclerAdapter<ItemModel,ItemAdapter.myViewHolder> {

    public String idItem;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ItemAdapter(FirebaseRecyclerOptions<ItemModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(myViewHolder holder, @SuppressLint("RecyclerView") int position, ItemModel model) {
        holder.name.setText(model.getItemName());
        holder.price.setText(model.getIprice());
        holder.discountPrice.setText(model.getIdiscountPrice());

        Glide.with(holder.image.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ItemDetail.class);
                intent.putExtra("itemKey",getRef(position).getKey());
                v.getContext().startActivity(intent);
            }
        });



    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView image;
        TextView name,price,discountPrice;


        View v;



        public myViewHolder(View itemView) {
            super(itemView);

            image = (ShapeableImageView)itemView.findViewById(R.id.img2);
            name = (TextView)itemView.findViewById(R.id.nameText);
            price = (TextView)itemView.findViewById(R.id.price);
            discountPrice = (TextView)itemView.findViewById(R.id.discountPrice);

            v = itemView;
        }
    }

}
