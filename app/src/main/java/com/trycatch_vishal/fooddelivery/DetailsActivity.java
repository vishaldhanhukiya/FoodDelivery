package com.trycatch_vishal.fooddelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private TextView numTxt;
    private TextView titleTxt;
    private TextView priceTxt;
    private TextView totalPrcTxt;
    private ImageView detailsImage;
    private TextView Detailinfo;
    private double unitPrice; // Adjust this with your actual unit price
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        numTxt = findViewById(R.id.numTxt);
        titleTxt = findViewById(R.id.titletxt);
        priceTxt = findViewById(R.id.prctxt);
        detailsImage = findViewById(R.id.imageView);
        totalPrcTxt = findViewById(R.id.totalprcrs);
        Detailinfo = findViewById(R.id.Detailinfo);
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String price = intent.getStringExtra("price");
            String image = intent.getStringExtra("image");
            String description = intent.getStringExtra("details");
            unitPrice = Double.parseDouble(price); // Obtain unit price from intent
            titleTxt.setText(title);
            priceTxt.setText("₹" + price);
            Detailinfo.setText(description); // Set the description
            Glide.with(this).load(image).into(detailsImage);
        }


        updateTotalPrice(); // Update total price initially

        // Set click listeners or implement other functionality as needed
        findViewById(R.id.addbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });

        findViewById(R.id.bckbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        findViewById(R.id.minbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseQuantity();
            }
        });

        findViewById(R.id.plusbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseQuantity();
            }
        });
    }

    private void updateTotalPrice() {
        double totalPrice = unitPrice * quantity;
        totalPrcTxt.setText("₹" + totalPrice);
    }

    private void addToCart() {
        // Show toast indicating item is added to cart
        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();

        // Create intent to start Cart_Activity activity
        Intent intent = new Intent(this, CartActivity.class);

        // Pass item title as extra
        intent.putExtra("title", titleTxt.getText().toString());

        // Start activity
        startActivity(intent);
    }

    private void decreaseQuantity() {
        // Implement your logic for decreasing quantity
        int currentQuantity = Integer.parseInt(numTxt.getText().toString());
        if (currentQuantity > 1) {
            quantity = currentQuantity - 1;
            numTxt.setText(String.valueOf(quantity));
            updateTotalPrice();
        }
    }

    private void increaseQuantity() {
        // Implement your logic for increasing quantity
        int currentQuantity = Integer.parseInt(numTxt.getText().toString());
        quantity = currentQuantity + 1;
        numTxt.setText(String.valueOf(quantity));
        updateTotalPrice();
    }
}