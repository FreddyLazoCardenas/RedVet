package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.PetLoverAppointmentEntity;
import com.papps.freddy_lazo.data.entity.RedVetAppointmentEntity;

import java.util.List;

public class PetLoverAppointmentResponse {

    @SerializedName("appointments")
    private List<PetLoverAppointmentEntity> appointmentList;

    @SerializedName("appointment")
    private RedVetAppointmentEntity appointment;


    public List<PetLoverAppointmentEntity> getAppointmentList() {
        return appointmentList;
    }

    public RedVetAppointmentEntity getAppointment() {
        return appointment;
    }
}
