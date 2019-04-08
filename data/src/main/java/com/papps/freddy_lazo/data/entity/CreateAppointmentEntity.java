package com.papps.freddy_lazo.data.entity;

import com.google.gson.annotations.SerializedName;

public class CreateAppointmentEntity {

    @SerializedName("id")
    private int id;
    @SerializedName("doctor_id")
    private String doctor_id;
    @SerializedName("pet_lover_id")
    private String pet_lover_id;
    @SerializedName("pet_by_pet_lover_id")
    private String pet_by_pet_lover_id;
    @SerializedName("date")
    private String date;
    @SerializedName("time")
    private String time;
    @SerializedName("type")
    private String type;
    @SerializedName("reason")
    private String reason;


    public int getId() {
        return id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public String getPet_lover_id() {
        return pet_lover_id;
    }

    public String getPet_by_pet_lover_id() {
        return pet_by_pet_lover_id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

}
