package com.trycatch_vishal.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartActivity extends AppCompatActivity implements Cart_Adaptor.AdaptorListener {
    RecyclerView RVCT;
    Cart_Adaptor cartAdaptor;
    Userdao userdao;
    ImageView bckbtn1, history;
    Button orderbtn;
    TextView textView5, textView4, textView6, totalFeeTxt, deliveryTxt, taxTxt, textView10, totalTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        RVCT = findViewById(R.id.RVCT);
        bckbtn1 = findViewById(R.id.bckbtn1);
        orderbtn = findViewById(R.id.orderbtn);
        textView5 = findViewById(R.id.textView5);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView10 = findViewById(R.id.textView10);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        taxTxt = findViewById(R.id.taxTxt);
        totalTxt = findViewById(R.id.totalTxt);
        history = findViewById(R.id.history);


        RVCT.setLayoutManager(new LinearLayoutManager(this));
        getWindow().setStatusBarColor(Integer.parseInt(String.valueOf(getColor(R.color.blue))));
        userdao = Userdatabase.getINSTANCE(this).getDao();

        List<POJOSTATERS> pojostaters = userdao.getAllItem();
        cartAdaptor = new Cart_Adaptor(this, pojostaters, this);
        RVCT.setAdapter(cartAdaptor);

        // Calculate total price
        double total = calculateTotalPrice(pojostaters);

        // Display total price
        totalFeeTxt.setText(String.valueOf(total));

        // Set delivery and tax
        deliveryTxt.setText("40");
        taxTxt.setText("20");

        // Calculate total with delivery and tax
        double totalPriceWithDeliveryAndTax = total + 40 + 20;

        // Display total with delivery and tax
        totalTxt.setText(String.valueOf(totalPriceWithDeliveryAndTax));

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, HistoryActivity.class));
            }
        });

        bckbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userdao.deleteAllItems();
                startActivity(new Intent(CartActivity.this, FinishSplashActivity.class));
                finish();
            }
        });
    }

    // Method to calculate total price
    private double calculateTotalPrice(List<POJOSTATERS> pojostaters) {
        double totalPrice = 0;
        for (POJOSTATERS item : pojostaters) {
            // Convert the String price to double
            double price = Double.parseDouble(item.getPrice());
            totalPrice += price;
        }
        return totalPrice;
    }

//    private long getUserId() {
//        // Implement method to retrieve user ID
//        return 0; // Replace with actual implementation
//    }
//
//    private void insertOrderHistory(OrderHistory orderHistory) {
//        // Implement method to insert order history into database
//        OrderHistorydao orderHistorydao = OrderHistorydatabase.getInstance(this).getOrderHistorydao();
//        orderHistorydao.Insert(orderHistory);
//    }

    @Override
    public void OnDelete(double id, int position) {

        POJOSTATERS deleteItem = cartAdaptor.pojostaters.get(position);// Get the price of the deleted item
        double deletedItemPrice = Double.parseDouble(deleteItem.getPrice());

        // Remove the item from the database
        userdao.Delete(id);
        // Remove the item from the adapter
        cartAdaptor.removeUser(position);
        // Recalculate total price
        double total = calculateTotalPrice(userdao.getAllItem());
        // Update totalFeeTxt
        totalFeeTxt.setText(String.valueOf(total));
        // Calculate total with delivery and tax
        double totalPriceWithDeliveryAndTax = total + 40 + 20;
        // Update totalTxt
        totalTxt.setText(String.valueOf(totalPriceWithDeliveryAndTax));
    }
}