package com.papps.freddy_lazo.data.entity;

import com.google.gson.annotations.SerializedName;

public class PetRegisterEntity {

    @SerializedName("pet_id")
    private int pet_id;
    @SerializedName("name")
    private String name;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("breed")
    private String breed;
    @SerializedName("photo")
    private String photo;


    public PetRegisterEntity(int pet_id, String name, String birthday, String breed, String photo) {
        this.pet_id = pet_id;
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.photo = photo;
    }
}
