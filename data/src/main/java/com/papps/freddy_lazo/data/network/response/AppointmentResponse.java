package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.AppointmentEntity;

import java.util.List;

public class AppointmentResponse {

    @SerializedName("appointments")
    private List<AppointmentEntity> appointmentList;

    @SerializedName("appointment")
    private AppointmentEntity appointment;


    public List<AppointmentEntity> getAppointmentList() {
        return appointmentList;
    }

    public AppointmentEntity getAppointment() {
        return appointment;
    }
}
