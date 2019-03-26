package com.papps.freddy_lazo.data.entity;

import com.google.gson.annotations.SerializedName;

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


    public List<PetEntity> getPetEntity() {
        return petEntity;
    }

    public void setPetEntity(List<PetEntity> petEntity) {
        this.petEntity = petEntity;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getType_document() {
        return type_document;
    }

    public void setType_document(String type_document) {
        this.type_document = type_document;
    }

    public String getNumber_document() {
        return number_document;
    }

    public void setNumber_document(String number_document) {
        this.number_document = number_document;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
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

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getConsultation_price() {
        return consultation_price;
    }

    public void setConsultation_price(String consultation_price) {
        this.consultation_price = consultation_price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTuition_number() {
        return tuition_number;
    }

    public void setTuition_number(String tuition_number) {
        this.tuition_number = tuition_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }
}
