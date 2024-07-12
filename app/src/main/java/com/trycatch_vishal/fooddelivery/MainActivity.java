package com.trycatch_vishal.fooddelivery;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView RVMA;
    private Main_Adaptor mainAdaptor;
    private ProgressBar progressBar;
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout imageContainer;
    private Handler handler;
    private int scrollSpeed = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RVMA = findViewById(R.id.RVMA);
        progressBar = findViewById(R.id.ProgressBar);
        horizontalScrollView = findViewById(R.id.horizontalScrollView);
        imageContainer = findViewById(R.id.imageContainer);
        getNewDatalist();
        getWindow().setStatusBarColor(getColor(R.color.primary));
        handler = new Handler();
        startAutoScroll();
    }

    private void getNewDatalist() {
        Call<ArrayList<POJOCATERING>> call = APIClient.getTryCatchInterface().getNewDatalist();
        call.enqueue(new Callback<ArrayList<POJOCATERING>>() {
            @Override
            public void onResponse(Call<ArrayList<POJOCATERING>> call, Response<ArrayList<POJOCATERING>> response) {
                if (response.code() == 200 && response.body() != null) {
                    ArrayList<POJOCATERING> data = response.body();
                    // Vertical RecyclerView
                    mainAdaptor = new Main_Adaptor(MainActivity.this, response.body());
                    RVMA.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    RVMA.setAdapter(mainAdaptor);
                    progressBar.setVisibility(View.GONE);
                } else if (response.code() == 401) {
                    Toast.makeText(MainActivity.this, "Unauthorized", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Authorized", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<POJOCATERING>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startAutoScroll() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Scroll horizontally
                horizontalScrollView.scrollBy(scrollSpeed, 0);

                // Check if reached the end, then reset scroll position
                if (horizontalScrollView.getScrollX() >= imageContainer.getWidth() - horizontalScrollView.getWidth()) {
                    horizontalScrollView.scrollTo(0, 0);
                }

                // Repeat the scroll
                handler.postDelayed(this, 10); // Adjust the delay as needed
            }
        }, 10); // Adjust the initial delay as needed
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the auto-scrolling when the activity is destroyed
        handler.removeCallbacksAndMessages(null);
    }
}
