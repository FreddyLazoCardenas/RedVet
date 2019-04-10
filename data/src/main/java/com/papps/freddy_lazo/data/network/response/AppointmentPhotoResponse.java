package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.AppointmentPhotoEntity;

public class AppointmentPhotoResponse {

    @SerializedName("appointment_photo")
    private AppointmentPhotoEntity appointment_photo;

    public AppointmentPhotoEntity getAppointment_photo() {
        return appointment_photo;
    }
}
