package com.khalej.hoguzat.model;

import com.google.gson.annotations.SerializedName;

public class content_servcies {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("price")
    String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
