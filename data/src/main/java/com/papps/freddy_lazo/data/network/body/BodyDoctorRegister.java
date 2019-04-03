package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;

import java.util.List;

public class BodyDoctorRegister {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("type_document")
    private String typeDocument;
    @SerializedName("number_document")
    private String numberDocument;
    @SerializedName("business_name")
    private String business_name;
    @SerializedName("address")
    private String address;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("consultation_price")
    private String consultationPrice;
    @SerializedName("consultation_time")
    private String consultationTime;
    @SerializedName("shower_price")
    private String shower_price;
    @SerializedName("shower_time")
    private String shower_time;
    @SerializedName("tuition_number")
    private String tuition_number;
    @SerializedName("description")
    private String description;
    @SerializedName("phone")
    private String phone;
    @SerializedName("photo")
    private String photo;
    @SerializedName("type")
    private String type;
    @SerializedName("attention")
    private String attention;
    @SerializedName("fcm_token")
    private String fcmToken;
    @SerializedName("device")
    private String device;
    @SerializedName("pets")
    private List<PetRegister> pets;
    @SerializedName("schedules")
    private List<ScheduleDoctorRegister> schedules;
    @SerializedName("services")
    private List<ServicesDoctorRegister> services;

    public BodyDoctorRegister(String email, String password, String firstName, String lastName, String typeDocument, String numberDocument, String business_name, String address, String latitude, String longitude, String consultationPrice, String consultationTime, String shower_price, String shower_time, String tuition_number, String description, String phone, String photo, String type, String attention, String fcmToken, String device, List<PetRegister> pets, List<ScheduleDoctorRegister> schedules, List<ServicesDoctorRegister> services) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.typeDocument = typeDocument;
        this.numberDocument = numberDocument;
        this.business_name = business_name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.consultationPrice = consultationPrice;
        this.consultationTime = consultationTime;
        this.shower_price = shower_price;
        this.shower_time = shower_time;
        this.tuition_number = tuition_number;
        this.description = description;
        this.phone = phone;
        this.photo = photo;
        this.type = type;
        this.attention = attention;
        this.fcmToken = fcmToken;
        this.device = device;
        this.pets = pets;
        this.schedules = schedules;
        this.services = services;
    }
}
