package com.khalej.hoguzat.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class content_stuff_realm extends RealmObject {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("nameca")
    String nameca;
    @SerializedName("image")
    String image;

    public String getNameca() {
        return nameca;
    }

    public void setNameca(String nameca) {
        this.nameca = nameca;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
