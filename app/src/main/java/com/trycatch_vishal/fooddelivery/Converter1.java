package com.trycatch_vishal.fooddelivery;

import androidx.room.TypeConverter;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class Converter1 {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static List<POJOSTATERS> fromString(String value) {
        Type listType = new TypeToken<List<POJOSTATERS>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<POJOSTATERS> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
