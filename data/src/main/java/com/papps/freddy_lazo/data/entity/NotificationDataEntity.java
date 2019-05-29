package com.papps.freddy_lazo.data.entity;

import com.google.gson.annotations.SerializedName;

public class NotificationDataEntity {

    @SerializedName("type")
    String type;
    @SerializedName("appointment_id")
    int appointment_id;
    @SerializedName("message")
    String message;

    public NotificationDataEntity(String type, int appointment_id, String message) {
        this.type = type;
        this.appointment_id = appointment_id;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public String getMessage() {
        return message;
    }
}
