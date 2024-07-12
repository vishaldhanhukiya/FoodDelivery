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

public class MainCourseActivity extends AppCompatActivity {
    RecyclerView RVMC;
    MainCourse_Adaptor mainCourseAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_course);
        RVMC = findViewById(R.id.RVMC);
        getMainCourse(2);
        getWindow().setStatusBarColor(Integer.parseInt(String.valueOf(getColor(R.color.blue))));
    }

    public void getMainCourse(int category_id) {
        Call<List<POJOSTATERS>> call = APIClient.getTryCatchInterface().getNewDataMN(category_id);
        call.enqueue(new Callback<List<POJOSTATERS>>() {
            @Override
            public void onResponse(Call<List<POJOSTATERS>> call, Response<List<POJOSTATERS>> response) {
                if (response.code() == 200 && response.body() != null) {
                    mainCourseAdaptor = new MainCourse_Adaptor(MainCourseActivity.this, response.body());
                    RVMC.setLayoutManager(new GridLayoutManager(MainCourseActivity.this,2));
                    RVMC.setAdapter(mainCourseAdaptor);
                } else if (response.code() == 401) {
                    Toast.makeText(MainCourseActivity.this, "Unauthorized", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainCourseActivity.this, "Authorized", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<POJOSTATERS>> call, Throwable t) {
                Toast.makeText(MainCourseActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}