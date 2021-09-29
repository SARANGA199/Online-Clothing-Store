package com.example.onlineclothingstore.item_and_category_management;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlineclothingstore.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ItemAdapterAdmin extends FirebaseRecyclerAdapter<ItemModel, ItemAdapterAdmin.myViewHolder> {

    public String idItem;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ItemAdapterAdmin(FirebaseRecyclerOptions<ItemModel> options) {
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
                Intent intent = new Intent(v.getContext(), ItemDetail.class);
                intent.putExtra("itemKey", getRef(position).getKey());
                v.getContext().startActivity(intent);
            }
        });


        //update Item
        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.image.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_item_popup))
                        //.setExpanded(true,1000)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50, 0, 50, 0)
                        .create();
                // dialogPlus.show();
                View view = dialogPlus.getHolderView();

                EditText itemname = view.findViewById(R.id.txtItemName);
                EditText priceN = view.findViewById(R.id.txtDESItem);
                EditText Disc = view.findViewById(R.id.txtDISCOUNT);
                ImageView imgI = view.findViewById(R.id.updateimg);

                // Picasso.get().load(holder.image).into(shapeableImageView);

                Glide.with(holder.image.getContext())
                        .load(model.getImage())
                        .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                        .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                        .into(imgI);


                Button btnUpdateItem = view.findViewById(R.id.btnUpdateItem);

                //set data to edittext
                itemname.setText(model.getItemName());
                priceN.setText(model.getIprice());
                Disc.setText(model.getIdiscountPrice());

                dialogPlus.show();

                btnUpdateItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("itemName", itemname.getText().toString());
                        map.put("iprice", priceN.getText().toString());
                        map.put("idiscountPrice", Disc.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Products")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.image.getContext(), "Updated Sucessfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.image.getContext(), "Error while Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }
        });

        //Delete item
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.image.getContext());

                builder.setTitle("Are you sure to delete ?");
                builder.setMessage("Deleted data can't be Undo ");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("Products")
                                .child(Objects.requireNonNull(getRef(position).getKey())).removeValue();

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.image.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();

            }
        });


    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one_admin, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView image;
        TextView name, price, discountPrice;
        Button btn1, btn2;


        View v;

        public myViewHolder(View itemView) {
            super(itemView);

            image = (ShapeableImageView) itemView.findViewById(R.id.img2A);
            name = (TextView) itemView.findViewById(R.id.nameTextA);
            price = (TextView) itemView.findViewById(R.id.priceA);
            discountPrice = (TextView) itemView.findViewById(R.id.discountPriceA);
            btn1 = (Button) itemView.findViewById(R.id.btnIedit);
            btn2 = (Button) itemView.findViewById(R.id.btnIdelete);

            v = itemView;
        }
    }

}
