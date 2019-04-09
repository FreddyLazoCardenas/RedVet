package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;

public class BodyFinishAppointment {

    @SerializedName("appointment_id")
    private int appointment_id;
    @SerializedName("diagnosis")
    private String diagnosis;

    public BodyFinishAppointment(int appointment_id, String diagnosis) {
        this.appointment_id = appointment_id;
        this.diagnosis = diagnosis;
    }
}
