package com.trycatch_vishal.fooddelivery;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SweetActivity extends AppCompatActivity {
    RecyclerView RVSW;
    Sweet_Adaptor sweetAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweet);
        RVSW = findViewById(R.id.RVSW);
        getSweetData(3);
        getWindow().setStatusBarColor(Integer.parseInt(String.valueOf(getColor(R.color.blue))));
    }

    public void getSweetData(int category_id) {
        Call<List<POJOSTATERS>> call = APIClient.getTryCatchInterface().getNewDataSW(category_id);
        call.enqueue(new Callback<List<POJOSTATERS>>() {
            @Override
            public void onResponse(Call<List<POJOSTATERS>> call, Response<List<POJOSTATERS>> response) {
                if (response.code() == 200 && response.body() != null) {
                    sweetAdaptor = new Sweet_Adaptor(SweetActivity.this, response.body());
                    RVSW.setLayoutManager(new GridLayoutManager(SweetActivity.this,2));
                    RVSW.setAdapter(sweetAdaptor);
                } else if (response.code() == 401) {
                    Toast.makeText(SweetActivity.this, "Unauthorized", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SweetActivity.this, "Authorized", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<POJOSTATERS>> call, Throwable t) {
                Toast.makeText(SweetActivity.this, "Mehant barabad ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}