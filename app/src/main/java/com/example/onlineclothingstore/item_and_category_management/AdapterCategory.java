package com.example.onlineclothingstore.item_and_category_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlineclothingstore.databinding.RowCategoryBinding;

import java.util.ArrayList;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.HolderCategory> {

    private Context context;
    private ArrayList<ModelCategory> categoryArrayList;

    //view binding
    private RowCategoryBinding binding;

    public AdapterCategory(Context context, ArrayList<ModelCategory> categoryArrayList) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
    }

    @NonNull
    @Override
    public HolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //bind row_category
        binding = RowCategoryBinding.inflate(LayoutInflater.from(context),parent,false);
        return new HolderCategory(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCategory holder, int position) {
        //get data
        ModelCategory model = categoryArrayList.get(position);
        String Description = model.getDescription();
        String category = model.getCategory();
        String id = model.getId();
        //long timestamp = model.getTimestamp();

        //set data
        holder.categoryTV.setText(category);

        //handle click,delete category
        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+category, Toast.LENGTH_SHORT).show();
                //delete
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }


    // view holder class to hold UI views for row_category.xml
    class HolderCategory extends RecyclerView.ViewHolder{

        //ui views of row_category.xml
        TextView categoryTV;
        ImageButton deletebtn;


        public HolderCategory(@NonNull View itemView) {
            super(itemView);

            //init ui views
            categoryTV = binding.categoryTV;
            deletebtn = binding.deletebtn;
        }
    }



}
