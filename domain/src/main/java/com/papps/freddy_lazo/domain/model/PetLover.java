package com.papps.freddy_lazo.domain.model;

import java.util.List;

public class PetLover {

    private int user_id;
    private String dni;
    private String address;
    private String phone;
    private String first_name;
    private String last_name;
    private String photo_url;
    private String email;
    private String api_token;
    private List<Pet> petList;

    public PetLover(int user_id, String dni, String address, String phone, String first_name, String last_name, String photo_url, String email, String api_token, List<Pet> petList) {
        this.user_id = user_id;
        this.dni = dni;
        this.address = address;
        this.phone = phone;
        this.first_name = first_name;
        this.last_name = last_name;
        this.photo_url = photo_url;
        this.email = email;
        this.api_token = api_token;
        this.petList = petList;
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

    public String getPhoto_url() {
        return photo_url;
    }

    public String getEmail() {
        return email;
    }

    public String getApi_token() {
        return api_token;
    }

    public List<Pet> getPetList() {
        return petList;
    }
}
