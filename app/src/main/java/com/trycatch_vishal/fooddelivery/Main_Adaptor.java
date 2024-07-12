package com.trycatch_vishal.fooddelivery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Placeholder;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Main_Adaptor extends RecyclerView.Adapter<Main_Adaptor.ViewHolder> {
    private Context context;
    private List<POJOCATERING> pojocatering;
    private ItemClick itemClick;

    public Main_Adaptor(Context context, List<POJOCATERING> pojocatering) {
        this.context = context;
        this.pojocatering = pojocatering;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mainsinglerow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemText.setText(pojocatering.get(position).getCatName());
        Glide.with(context).load(pojocatering.get(position).getCatImage()).into(holder.itemImage);

        holder.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    Intent intent = new Intent(context, StarterActivity.class);
                    context.startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(context, MainCourseActivity.class);
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, SweetActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pojocatering.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemText;
        CardView card1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemText = itemView.findViewById(R.id.itemText);
            card1 = itemView.findViewById(R.id.card1);
        }
    }

    interface ItemClick {
        String onItemClick();
    }
}
