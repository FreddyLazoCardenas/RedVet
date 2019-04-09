package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;

public class BodyCancelAppointment {

    @SerializedName("appointment_id")
    private int appointment_id;
    @SerializedName("reason")
    private String reason;

    public BodyCancelAppointment(int appointment_id, String reason) {
        this.appointment_id = appointment_id;
        this.reason = reason;
    }

    public BodyCancelAppointment(int appointment_id) {
        this.appointment_id = appointment_id;
    }
}
