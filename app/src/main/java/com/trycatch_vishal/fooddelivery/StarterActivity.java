package com.trycatch_vishal.fooddelivery;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarterActivity extends AppCompatActivity {
    RecyclerView RVST;
    Starter_Adaptor starterAdaptor;
    Userdatabase userdatabase;
    Userdao userdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
        RVST = findViewById(R.id.RVST);
        userdatabase = Userdatabase.getINSTANCE(this);
        userdao = userdatabase.getDao();
        getStarter(1);
        getWindow().setStatusBarColor(Integer.parseInt(String.valueOf(getColor(R.color.blue))));
    }

    public void getStarter(int category_id) {
        Call<ArrayList<POJOSTATERS>> call = APIClient.getTryCatchInterface().getNewDataST(category_id);
        call.enqueue(new Callback<ArrayList<POJOSTATERS>>() {
            @Override
            public void onResponse(Call<ArrayList<POJOSTATERS>> call, Response<ArrayList<POJOSTATERS>> response) {
                if (response.code() == 200 && response.body() != null) {
                    starterAdaptor = new Starter_Adaptor(StarterActivity.this, response.body());
                    RVST.setLayoutManager(new GridLayoutManager(StarterActivity.this, 2));
                    RVST.setAdapter(starterAdaptor);
                } else if (response.code() == 401) {
                    Toast.makeText(StarterActivity.this, "Unauthorized", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StarterActivity.this, "Authorized", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<POJOSTATERS>> call, Throwable t) {
                Toast.makeText(StarterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

