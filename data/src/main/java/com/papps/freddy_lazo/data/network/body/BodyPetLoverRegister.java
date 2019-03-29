package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.PetEntity;

import java.util.List;

public class BodyPetLoverRegister {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("dni")
    private String dni;
    @SerializedName("address")
    private String address;
    @SerializedName("phone")
    private String phone;
    @SerializedName("fcm_token")
    private String fcmToken;
    @SerializedName("device")
    private String device;

    // TODO: 3/26/19 crear entidad de mandar la data de mascotas
    @SerializedName("pets")
    private List<PetEntity> pets;

    public BodyPetLoverRegister(String email, String password, String firstName, String lastName, String dni, String address, String phone, String fcmToken, String device, List<PetEntity> pets) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.address = address;
        this.phone = phone;
        this.fcmToken = fcmToken;
        this.device = device;
        this.pets = pets;
    }
}