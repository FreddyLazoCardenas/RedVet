package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;

public class BodyQualifyAppointment {

    @SerializedName("appointment_id")
    private int appointment_id;
    @SerializedName("qualification")
    private int qualification;

    public BodyQualifyAppointment(int appointment_id, int qualification) {
        this.appointment_id = appointment_id;
        this.qualification = qualification;
    }
}
