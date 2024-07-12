package com.trycatch_vishal.fooddelivery;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class APIClient {
    public static TryCatchInterface tryCatchInterface;

    public static TryCatchInterface getTryCatchInterface() {
        if (tryCatchInterface == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(80, TimeUnit.SECONDS)
                    .connectTimeout(80, TimeUnit.SECONDS)
                    .build();
            Gson gson = new GsonBuilder()
                    .setLenient().create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://mapi.trycatchtech.com/v3/food_catering/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            tryCatchInterface = retrofit.create(TryCatchInterface.class);
        }
        return tryCatchInterface;
    }

    public interface TryCatchInterface {
        @GET("category_list")
        Call<ArrayList<POJOCATERING>> getNewDatalist();

        @GET("product_list")
        Call<ArrayList<POJOSTATERS>> getNewDataST(@Query("category_id") int category);

        @GET("product_list")
        Call<List<POJOSTATERS>> getNewDataMN(@Query("category_id") int category);

        @GET("product_list")
        Call<List<POJOSTATERS>> getNewDataSW(@Query("category_id") int category);
    }
}