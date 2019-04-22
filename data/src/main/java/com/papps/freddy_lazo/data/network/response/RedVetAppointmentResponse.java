package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.RedVetDetailAppointmentEntity;

public class RedVetAppointmentResponse {

    @SerializedName("appointment")
    private RedVetDetailAppointmentEntity appointment;

    public RedVetDetailAppointmentEntity getAppointment() {
        return appointment;
    }
}