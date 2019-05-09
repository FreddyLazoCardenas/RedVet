package com.papps.freddy_lazo.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PetLoverEntity {

    @SerializedName("user_id")
    private int user_id;
    @SerializedName("dni")
    private String dni;
    @SerializedName("address")
    private String address;
    @SerializedName("phone")
    private String phone;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("email")
    private String email;
    @SerializedName("photo_url")
    private String photo_url;
    @SerializedName("api_token")
    private String api_token;
    @SerializedName("attentions")
    private Integer attentions;
    @SerializedName("pets")
    private List<PetEntity> petEntity;

    public Integer getAttentions() {
        return attentions;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getDni() {
        return dni;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public String getApi_token() {
        return api_token;
    }

    public List<PetEntity> getPetEntity() {
        return petEntity;
    }
}
