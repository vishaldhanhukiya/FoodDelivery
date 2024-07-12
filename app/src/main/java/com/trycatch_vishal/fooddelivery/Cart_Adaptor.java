package com.trycatch_vishal.fooddelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Cart_Adaptor extends RecyclerView.Adapter<Cart_Adaptor.ViewHolder> {
    Context context;
    List<POJOSTATERS> pojostaters;
    AdaptorListener adaptorListener;

    public Cart_Adaptor(Context context, List<POJOSTATERS> pojostaters, AdaptorListener adaptorListener) {
        this.context = context;
        this.pojostaters = pojostaters;
        this.adaptorListener = adaptorListener;
    }

    public void removeUser(int position) {
        pojostaters.remove(position);
        notifyDataSetChanged();
    }

//    public void clearItems() {
//        pojostaters.clear();
//        notifyDataSetChanged(); // Notify adapter that dataset has changed
//    }

    public interface AdaptorListener {
        void OnDelete(double id, int pos);
    }

    @NonNull
    @Override
    public Cart_Adaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cart_Adaptor.ViewHolder holder, int position) {
        POJOSTATERS selectItem = pojostaters.get(position);
        holder.titleTextView.setText(selectItem.getTitle());
        holder.priceTextView.setText("₹" + selectItem.getPrice());
        Glide.with(context).load(selectItem.getImage().get(0)).into(holder.cartimg);
        holder.minbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(holder.numTxt1.getText().toString());
                if (currentQuantity > 1) {
                    currentQuantity--;
                    holder.numTxt1.setText(String.valueOf(currentQuantity));
                    updatePrice(currentQuantity, holder);
                }
            }
        });
        holder.plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = Integer.parseInt(holder.numTxt1.getText().toString());
                currentQuantity++;
                holder.numTxt1.setText(String.valueOf(currentQuantity));
                updatePrice(currentQuantity, holder);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptorListener.OnDelete(pojostaters.get(position).getId(), position);
            }
        });
    }

    private void updatePrice(int quantity, ViewHolder holder) {
        // Get the POJOSTATERS object corresponding to this ViewHolder
        POJOSTATERS item = pojostaters.get(holder.getAdapterPosition());

        // Get the price of the item
        double itemPrice = Double.parseDouble(item.getPrice());

        // Calculate the total price based on the quantity
        double totalPrice = quantity * itemPrice;

        // Update the price TextView
        holder.priceTextView.setText("₹" + totalPrice);
    }

    @Override
    public int getItemCount() {
        return pojostaters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, priceTextView, plusbtn, minbtn, numTxt1, Quantity;
        ImageView delete, cartimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            delete = itemView.findViewById(R.id.delete);
            minbtn = itemView.findViewById(R.id.minbtn);
            plusbtn = itemView.findViewById(R.id.plusbtn);
            numTxt1 = itemView.findViewById(R.id.numTxt1);
            Quantity = itemView.findViewById(R.id.Qunty);
            cartimg = itemView.findViewById(R.id.cartimg);
        }
    }
}
