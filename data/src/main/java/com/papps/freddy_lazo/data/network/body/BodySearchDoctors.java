package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.PetEntity;

import java.util.ArrayList;
import java.util.List;

public class BodySearchDoctors {

    @SerializedName("type")
    private ArrayList<String> type;
    @SerializedName("services")
    private ArrayList<Integer> services;
    @SerializedName("pets")
    private ArrayList<Integer> pets;
    @SerializedName("text")
    private String text;

    public BodySearchDoctors(ArrayList<String> type, ArrayList<Integer> services, ArrayList<Integer> pets, String text) {
        this.type = type;
        this.services = services;
        this.pets = pets;
        this.text = text;
    }
}
