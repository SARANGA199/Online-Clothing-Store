package com.example.onlineclothingstore.request_and_review_management;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineclothingstore.R;
import com.example.onlineclothingstore.item_and_category_management.ItemDetail;
import com.example.onlineclothingstore.request_and_review_management.DBHelper.DBHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {
    private ArrayList<DBHelper> arrayList;
    private Context context;
    private DBHelper dbHelper;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public ListAdapter(ArrayList<DBHelper> arrayList,Context  context) {
        this.arrayList = arrayList;
        this.context= context;
    }
    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_items,parent,false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        Intent i = new Intent(context, SecondActivity.class);
        String s1 = arrayList.get(position).getType();
        holder.textList.setText(s1);
        holder.editList_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = arrayList.get(position);
                i.putExtra("Button","Edit");
                i.putExtra("type",dbHelper.getType());
                i.putExtra("size",dbHelper.getSize());
                i.putExtra("quantity",dbHelper.getQuantity());
                i.putExtra("description",dbHelper.getDescription());
                context.startActivity(i);
                FirstActivity.Fa.finish();
            }
        });

        holder.readList_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = arrayList.get(position);
                i.putExtra("Button","Read");
                i.putExtra("type",dbHelper.getType());
                i.putExtra("size",dbHelper.getSize());
                i.putExtra("quantity",dbHelper.getQuantity());
                i.putExtra("description",dbHelper.getDescription());
                context.startActivity(i);
                FirstActivity.Fa.finish();
            }
        });
        holder.deleteList_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FirstActivity.class);
                v.getContext().startActivity(intent);
                dbHelper = arrayList.get(position);
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference =firebaseDatabase.getReference("Requests");
                String temp =dbHelper.getType();
                databaseReference.child(temp).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
//                            Intent intent = new Intent(v.getContext(), FirstActivity.class);
//                            v.getContext().startActivity(intent);
                            Toast.makeText(context,"Data Removed",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Failed to remove data",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                FirstActivity.cleanList();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ListHolder extends RecyclerView.ViewHolder{
        private TextView textList;
        private Button editList_items,deleteList_items,readList_item;
        public ListHolder(@NonNull View itemView ) {
            super(itemView);
            textList =(TextView) itemView.findViewById(R.id.textItem);
            editList_items =(Button) itemView.findViewById(R.id.editList_items);
            deleteList_items = (Button) itemView.findViewById(R.id.deleteList_items);
            readList_item = (Button) itemView.findViewById(R.id.readList_items);
        }

    }
}
