package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.PetLoverAppointmentEntity;

public class QualificationResponse {

    @SerializedName("appointment")
    private PetLoverAppointmentEntity appointment;

    public PetLoverAppointmentEntity getAppointment() {
        return appointment;
    }
}
