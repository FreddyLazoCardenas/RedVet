package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;

public class BodyAppointment {

    @SerializedName("doctor_id")
    private int doctorId;
    @SerializedName("pet_by_pet_lover_id")
    private int petLoverId;
    @SerializedName("date")
    private String date;
    @SerializedName("time")
    private String time;
    @SerializedName("type")
    private String type;
    @SerializedName("reason")
    private String reason;

    public BodyAppointment(int doctorId, int petLoverId, String date, String time, String type, String reason) {
        this.doctorId = doctorId;
        this.petLoverId = petLoverId;
        this.date = date;
        this.time = time;
        this.type = type;
        this.reason = reason;
    }
}
