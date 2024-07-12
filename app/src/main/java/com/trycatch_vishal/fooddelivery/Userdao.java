package com.trycatch_vishal.fooddelivery;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Userdao {
    @Insert
    void Insert(POJOSTATERS item);

    @Query("Select * from POJOSTATERS")
    List<POJOSTATERS> getAllItem();

    @Query("Delete from POJOSTATERS where id =:id")
    void Delete(double id);

    @Query("DELETE FROM pojostaters")
    void deleteAllItems();
}
