package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;

public class BodyUploadPhoto {

    @SerializedName("appointment_id")
    private int appointment_id;
    @SerializedName("photo")
    private String photo;

    public BodyUploadPhoto(int appointment_id, String photo) {
        this.appointment_id = appointment_id;
        this.photo = photo;
    }

}
