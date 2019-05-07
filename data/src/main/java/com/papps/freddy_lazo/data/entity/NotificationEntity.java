package com.papps.freddy_lazo.data.entity;

import com.google.gson.annotations.SerializedName;

public class NotificationEntity {

    @SerializedName("id")
    Integer id;
    @SerializedName("type")
    String type;
    @SerializedName("appointment_id")
    String appointment_id;
    @SerializedName("message")
    String message;
    @SerializedName("time")
    String time;
    @SerializedName("from_doctor")
    boolean from_doctor;

    public NotificationEntity(Integer id, String type, String appointment_id, String message, String time, boolean from_doctor) {
        this.id = id;
        this.type = type;
        this.appointment_id = appointment_id;
        this.message = message;
        this.time = time;
        this.from_doctor = from_doctor;
    }


    public Integer getId() {
        return id;
    }

    public boolean isFrom_doctor() {
        return from_doctor;
    }

    public String getType() {
        return type;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
