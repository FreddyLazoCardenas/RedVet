package com.papps.freddy_lazo.data.entity;

import com.google.gson.annotations.SerializedName;

public class LoginEntity {

    @SerializedName("doctor")
    private
    DoctorEntity doctorEntity;

    public DoctorEntity getDoctorEntity() {
        return doctorEntity;
    }

    public void setDoctorEntity(DoctorEntity doctorEntity) {
        this.doctorEntity = doctorEntity;
    }
}
