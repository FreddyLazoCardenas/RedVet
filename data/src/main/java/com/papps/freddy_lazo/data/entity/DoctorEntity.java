package com.papps.freddy_lazo.data.entity;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;

import java.util.List;

public class DoctorEntity {

    @SerializedName("user_id")
    private int user_id;
    @SerializedName("type_document")
    private String type_document;
    @SerializedName("number_document")
    private String number_document;
    @SerializedName("business_name")
    private String business_name;
    @SerializedName("address")
    private String address;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("consultation_price")
    private String consultation_price;
    @SerializedName("phone")
    private String phone;
    @SerializedName("type")
    private String type;
    @SerializedName("tuition_number")
    private String tuition_number;
    @SerializedName("description")
    private String description;
    @SerializedName("attention")
    private String attention;
    @SerializedName("available")
    private String available;
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
    @SerializedName("pets")
    private List<PetEntity> petEntity;
    @SerializedName("schedules")
    private List<ScheduleEntity> schedules;
    @SerializedName("services")
    private List<ServicesDoctorEntity> services;


    public List<PetEntity> getPetEntity() {
        return petEntity;
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

    public void setAddress(String address) {
        this.address = address;
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

    public List<ScheduleEntity> getSchedules() {
        return schedules;
    }

    public List<ServicesDoctorEntity> getServices() {
        return services;
    }
}
