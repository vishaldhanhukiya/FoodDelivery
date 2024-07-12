package com.trycatch_vishal.fooddelivery;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "pojostaters")
public class POJOSTATERS {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @SerializedName("image")
    @ColumnInfo(name = "image")
    List<String> image;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    String title;

    @SerializedName("price")
    @ColumnInfo(name = "price")
    String price;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public List<String> getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void add(POJOSTATERS item) {
    }
}