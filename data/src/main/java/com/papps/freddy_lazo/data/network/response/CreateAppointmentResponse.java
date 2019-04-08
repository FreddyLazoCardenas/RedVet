package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.CreateAppointmentEntity;

public class CreateAppointmentResponse {

    @SerializedName("appointment")
    private CreateAppointmentEntity appointment;

    public CreateAppointmentEntity getAppointment() {
        return appointment;
    }
}
