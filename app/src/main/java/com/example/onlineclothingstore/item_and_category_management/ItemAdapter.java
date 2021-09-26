package com.example.onlineclothingstore.item_and_category_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineclothingstore.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

     Context context;
     List<ItemModel> itemModelList;

     //constractor


    public ItemAdapter(Context context, List<ItemModel> itemModelList) {
        this.context = context;
        this.itemModelList = itemModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one,parent,false);
        //design row connectivity

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //bind
        ItemModel itemModel = itemModelList.get(position);
        holder.itxtitem3.setText(itemModel.getItemBrand());
        holder.itxtitem1.setText(itemModel.getItemName());
        holder.itxtitem2.setText(itemModel.getItemPrice());


        String imageUri = null;
        imageUri = itemModel.getImage();
        Picasso.get().load(imageUri).into(holder.iimg1);

    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //design
        ImageView iimg1;
        TextView itxtitem1;
        TextView itxtitem2;
        TextView itxtitem3;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iimg1 = (ImageView) itemView.findViewById(R.id.img1);
            itxtitem1 = (TextView) itemView.findViewById(R.id.txtitem1);
            itxtitem2 = (TextView) itemView.findViewById(R.id.txtitem2);
            itxtitem3 = (TextView) itemView.findViewById(R.id.txtitem3);
        }
    }
}
