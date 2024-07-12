package com.trycatch_vishal.fooddelivery;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {OrderHistory.class}, version = 1)
@TypeConverters({Converter1.class})
public abstract class OrderHistorydatabase extends RoomDatabase {
    public abstract OrderHistorydao getOrderHistorydao();

    private static OrderHistorydatabase INSTANCE;

    public static synchronized OrderHistorydatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            OrderHistorydatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}