package com.papps.freddy_lazo.redvet.model;

public class CreateAppointmentModel {

    private int id;
    private String doctor_id;
    private String pet_lover_id;
    private String pet_by_pet_lover_id;
    private String date;
    private String time;
    private String type;
    private String reason;


    public CreateAppointmentModel(int id, String doctor_id, String pet_lover_id, String pet_by_pet_lover_id, String date, String time, String type, String reason) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.pet_lover_id = pet_lover_id;
        this.pet_by_pet_lover_id = pet_by_pet_lover_id;
        this.date = date;
        this.time = time;
        this.type = type;
        this.reason = reason;
    }

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
