package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.DoctorEntity;
import com.papps.freddy_lazo.data.entity.ServicesEntity;

import java.util.List;

public class ServicesResponse {

    @SerializedName("services")
    private List<ServicesEntity> servicesEntityList;

    public List<ServicesEntity> getServicesEntityList() {
        return servicesEntityList;
    }

    public void setServicesEntityList(List<ServicesEntity> servicesEntityList) {
        this.servicesEntityList = servicesEntityList;
    }
}
