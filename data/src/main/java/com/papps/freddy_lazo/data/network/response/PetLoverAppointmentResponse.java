package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.PetLoverAppointmentEntity;

import java.util.List;

public class PetLoverAppointmentResponse {

    @SerializedName("appointments")
    private List<PetLoverAppointmentEntity> appointmentList;


    public List<PetLoverAppointmentEntity> getAppointmentList() {
        return appointmentList;
    }


}
