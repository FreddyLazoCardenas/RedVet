package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.DoctorEntity;
import com.papps.freddy_lazo.data.entity.PetLoverEntity;

public class UpdateResponse {

    @SerializedName("doctorEntity")
    private DoctorEntity doctorEntity;

    @SerializedName("petLoverEntity")
    private PetLoverEntity petLoverEntity;

    public DoctorEntity getDoctorEntity() {
        return doctorEntity;
    }

    public PetLoverEntity getPetLoverEntity() {
        return petLoverEntity;
    }
}
