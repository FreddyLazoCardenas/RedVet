package com.papps.freddy_lazo.data.entity;


import com.google.gson.annotations.SerializedName;

public class AppointmentPhotoEntity {

    @SerializedName("id")
    private int id;
    @SerializedName("appointment_id")
    private String appointment_id;
    @SerializedName("photo_url")
    private String photo_url;

    public int getId() {
        return id;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public String getPhoto_url() {
        return photo_url;
    }
}
