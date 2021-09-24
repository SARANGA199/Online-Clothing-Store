package com.example.onlineclothingstore.user_and_payment_management;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineclothingstore.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;


public class CardAdapter extends FirebaseRecyclerAdapter<Cards,CardAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CardAdapter(@NonNull FirebaseRecyclerOptions<Cards> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView")final int position, @NonNull Cards model) {
        holder.cardname.setText(model.getCardname());
        holder.cardnumber.setText(model.getDecNumber());
        holder.expdate.setText(model.getExpdate());
        holder.cvv.setText(model.getCv());


        holder.updatecrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.cvv.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();

                dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.txtcrdnme);
                EditText number = view.findViewById(R.id.txtcrdnum);
                EditText expdate = view.findViewById(R.id.txtcrdexp);
                EditText cv = view.findViewById(R.id.txtcrdcv);

                Button btnUpdate = view.findViewById(R.id.editcrd);

                name.setText(model.getCardname());
                number.setText(model.getDecNumber());
                expdate.setText(model.getExpdate());
                cv.setText(model.getCv());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("cardname",name.getText().toString());
                        map.put("number",number.getText().toString());
                        map.put("expdate",expdate.getText().toString());
                        map.put("cv",cv.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("paymentCards")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.cardname.getContext(), "Data Updated Successfully.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.cardname.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }
        });

        holder.removecrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.cardname.getContext());
                builder.setTitle("Are You Sure ?");
                builder.setMessage("Deleted data can't be Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("paymentCards")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.cardname.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView cardname, cardnumber, expdate, cvv;

        TextView updatecrd,removecrd;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            cardname = itemView.findViewById(R.id.txtcardname);
            cardnumber = itemView.findViewById(R.id.txtcardnumber);
            expdate = itemView.findViewById(R.id.txtexpdate);
            cvv = itemView.findViewById(R.id.txtcv);

            updatecrd = (TextView)itemView.findViewById(R.id.updatecrd);
            removecrd = (TextView)itemView.findViewById(R.id.removecrd);

        }
    }

}
