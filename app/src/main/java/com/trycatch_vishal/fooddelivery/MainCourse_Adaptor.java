package com.trycatch_vishal.fooddelivery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

public class MainCourse_Adaptor extends RecyclerView.Adapter<MainCourse_Adaptor.ViewHolder> {
    Context context;
    List<POJOSTATERS> pojostater;

    public MainCourse_Adaptor(Context context, List<POJOSTATERS> pojostater) {
        this.context = context;
        Collections.sort(pojostater, (item1, item2) -> item1.getTitle().compareToIgnoreCase(item2.getTitle()));
        this.pojostater = pojostater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.productsinglerow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtsi.setText(pojostater.get(position).getTitle());
        Glide.with(context).load(pojostater.get(position).getImage().get(0)).into(holder.imgsi);
        holder.presi1.setText("₹" + pojostater.get(position).getPrice());
        holder.txtsi.setText(pojostater.get(position).getTitle());
        Glide.with(context).load(pojostater.get(position).getImage().get(0)).into(holder.imgsi);
        holder.presi1.setText("₹" + pojostater.get(position).getPrice());
        holder.card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("title", pojostater.get(position).getTitle());
                intent.putExtra("price", pojostater.get(position).getPrice());
                intent.putExtra("image", pojostater.get(position).getImage().get(0));
                intent.putExtra("details", pojostater.get(position).getDescription());

                context.startActivity(intent);
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                POJOSTATERS selectedItem = pojostater.get(position);
                Userdatabase userdatabase = Userdatabase.getINSTANCE(context);
                userdatabase.getDao().Insert(selectedItem);
                Intent intent = new Intent(context, CartActivity.class);
                intent.putExtra("item", true);
                context.startActivity(intent);
                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pojostater.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtsi, presi1;
        ImageView imgsi, plus;
        CardView card2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtsi = itemView.findViewById(R.id.txtsi);
            card2 = itemView.findViewById(R.id.card2);
            presi1 = itemView.findViewById(R.id.presi1);
            imgsi = itemView.findViewById(R.id.imgsi);
            plus = itemView.findViewById(R.id.plus);
        }
    }
}
