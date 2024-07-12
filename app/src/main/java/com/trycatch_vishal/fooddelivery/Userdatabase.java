package com.trycatch_vishal.fooddelivery;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {POJOSTATERS.class}, version = 1)
@TypeConverters({Converter.class})

public abstract class Userdatabase extends RoomDatabase {
    public abstract Userdao getDao();

    public static Userdatabase INSTANCE;

    public static Userdatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, Userdatabase.class, "userdatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}