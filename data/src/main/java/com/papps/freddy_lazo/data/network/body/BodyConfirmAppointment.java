package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;

public class BodyConfirmAppointment {

    @SerializedName("appointment_id")
    private int appointment_id;

    public BodyConfirmAppointment(int appointment_id) {
        this.appointment_id = appointment_id;
    }

}
