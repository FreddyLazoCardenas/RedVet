package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;

public class BodyRedVetChat {

    @SerializedName("appointment_id")
    private int appointment_id;
    @SerializedName("message")
    private String message;

    public BodyRedVetChat(int appointment_id, String message) {
        this.appointment_id = appointment_id;
        this.message = message;
    }
}
