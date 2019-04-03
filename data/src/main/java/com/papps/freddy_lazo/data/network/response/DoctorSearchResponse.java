package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.DoctorEntity;

import java.util.ArrayList;

public class DoctorSearchResponse {

    @SerializedName("doctors")
    private ArrayList<DoctorEntity> doctorEntityList;

    public ArrayList<DoctorEntity> getDoctorEntity() {
        return doctorEntityList;
    }

    public void setDoctorEntity(ArrayList<DoctorEntity> doctorEntityList) {
        this.doctorEntityList = doctorEntityList;
    }
}
