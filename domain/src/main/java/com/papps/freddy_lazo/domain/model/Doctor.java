package com.papps.freddy_lazo.domain.model;

import java.util.List;

public class Doctor {

    private int user_id;
    private String type_document;
    private String number_document;
    private String business_name;
    private String address;
    private String latitude;
    private String longitude;
    private String consultation_price;
    private String phone;
    private String type;
    private String tuition_number;
    private String description;
    private String attention;
    private String available;
    private String first_name;
    private String last_name;
    private String email;
    private String photo_url;
    private String api_token;
    private List<Pet> petList;

    public Doctor(int user_id, String type_document, String number_document, String business_name, String address, String latitude, String longitude, String consultation_price, String phone, String type, String tuition_number, String description, String attention
            , String available, String first_name, String last_name, String email, String photo_url, String api_token, List<Pet> petList ) {
        this.user_id = user_id;
        this.type_document = type_document;
        this.number_document = number_document;
        this.business_name = business_name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.consultation_price = consultation_price;
        this.phone = phone;
        this.type = type;
        this.tuition_number = tuition_number;
        this.description = description;
        this.attention = attention;
        this.available = available;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.photo_url = photo_url;
        this.api_token = api_token;
        this.petList = petList;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getType_document() {
        return type_document;
    }

    public String getNumber_document() {
        return number_document;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getConsultation_price() {
        return consultation_price;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }

    public String getTuition_number() {
        return tuition_number;
    }

    public String getDescription() {
        return description;
    }

    public String getAttention() {
        return attention;
    }

    public String getAvailable() {
        return available;
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
}
