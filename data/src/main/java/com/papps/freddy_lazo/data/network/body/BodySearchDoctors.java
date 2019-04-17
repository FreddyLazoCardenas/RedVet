package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.PetEntity;

import java.util.ArrayList;
import java.util.List;

public class BodySearchDoctors {

    @SerializedName("type")
    private List<String> type;
    @SerializedName("services")
    private List<Integer> services;
    @SerializedName("pets")
    private List<Integer> pets;
    @SerializedName("text")
    private String text;

    public BodySearchDoctors(List<String> type, List<Integer> services, List<Integer> pets, String text) {
        this.type = type;
        this.services = services;
        this.pets = pets;
        this.text = text;
    }
}
