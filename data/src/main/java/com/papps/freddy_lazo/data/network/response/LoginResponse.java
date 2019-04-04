package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.DoctorEntity;
import com.papps.freddy_lazo.data.entity.PetLoverEntity;

public class LoginResponse {

    @SerializedName("doctor")
    private DoctorEntity doctorEntity;

    @SerializedName("pet-lover")
    private PetLoverEntity petLoverEntity;

    public DoctorEntity getDoctorEntity() {
        return doctorEntity;
    }

    public PetLoverEntity getPetLoverEntity() {
        return petLoverEntity;
    }
}
