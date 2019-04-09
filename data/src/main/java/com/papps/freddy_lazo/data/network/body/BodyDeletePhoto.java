package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;

public class BodyDeletePhoto {

    @SerializedName("appointment_id")
    private int appointment_id;
    @SerializedName("appointment_photo_id")
    private int appointment_photo_id;

    public BodyDeletePhoto(int appointment_id, int appointment_photo_id) {
        this.appointment_id = appointment_id;
        this.appointment_photo_id = appointment_photo_id;
    }

}
