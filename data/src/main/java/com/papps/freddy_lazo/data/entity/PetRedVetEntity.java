package com.papps.freddy_lazo.data.entity;

import com.google.gson.annotations.SerializedName;

public class PetRedVetEntity {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("photo_url")
    private String photo_url;
    @SerializedName("available")
    private String available;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public String getAvailable() {
        return available;
    }
}
