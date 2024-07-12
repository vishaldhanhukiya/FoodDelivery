package com.trycatch_vishal.fooddelivery;

import com.google.gson.annotations.SerializedName;

public class POJOCATERING {
    @SerializedName("id")
    String id;
    @SerializedName("cat_name")
    String catName;
    @SerializedName("cat_image")
    String catImage;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }

    public String getCatImage() {
        return catImage;
    }
}
