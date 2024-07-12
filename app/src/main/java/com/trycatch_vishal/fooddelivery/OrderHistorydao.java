package com.trycatch_vishal.fooddelivery;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface OrderHistorydao {
    @Insert
    void Insert(OrderHistory orderHistory);

    @Query("SELECT * FROM order_history ORDER BY orderDate DESC")
    LiveData<List<OrderHistory>> getAllOrderHistory();
}