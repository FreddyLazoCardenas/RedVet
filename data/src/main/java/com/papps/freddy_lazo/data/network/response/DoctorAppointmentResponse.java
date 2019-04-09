package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.DoctorAppointmentEntity;
import com.papps.freddy_lazo.data.entity.PetLoverAppointmentEntity;

import java.util.List;

public class DoctorAppointmentResponse {

    @SerializedName("appointments")
    private List<DoctorAppointmentEntity> appointmentList;

    @SerializedName("appointment")
    private DoctorAppointmentEntity appointment;


    public List<DoctorAppointmentEntity> getAppointmentList() {
        return appointmentList;
    }

    public DoctorAppointmentEntity getAppointment() {
        return appointment;
    }
}
