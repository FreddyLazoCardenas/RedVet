package com.papps.freddy_lazo.data.entity;

import com.google.gson.annotations.SerializedName;

public class ServicesEntity {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("available")
    private String available;


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

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
