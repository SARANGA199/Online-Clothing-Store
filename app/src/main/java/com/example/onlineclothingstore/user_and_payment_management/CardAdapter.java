package com.example.onlineclothingstore.user_and_payment_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineclothingstore.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    Context context;

    ArrayList<Cards> list;

    public CardAdapter(Context context, ArrayList<Cards> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CardViewHolder holder, int position) {

        Cards cards = list.get(position);
        holder.cardname.setText(cards.getCardname());
        holder.cardnumber.setText(cards.getNumber());
        holder.expdate.setText(cards.getExpdate());
        holder.cvv.setText(cards.getCv());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{

        TextView cardname, cardnumber, expdate, cvv;

        public CardViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            cardname = itemView.findViewById(R.id.txtcardname);
            cardnumber = itemView.findViewById(R.id.txtcardnumber);
            expdate = itemView.findViewById(R.id.txtexpdate);
            cvv = itemView.findViewById(R.id.txtcv);
        }
    }
}
