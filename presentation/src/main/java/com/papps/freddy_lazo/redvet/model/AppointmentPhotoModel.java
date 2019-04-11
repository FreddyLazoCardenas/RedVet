package com.papps.freddy_lazo.redvet.model;

public class AppointmentPhotoModel {

    private int id;
    private String appointment_id;
    private String photo_url;

    public AppointmentPhotoModel(int id, String appointment_id, String photo_url) {
        this.id = id;
        this.appointment_id = appointment_id;
        this.photo_url = photo_url;
    }

    public int getId() {
        return id;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public String getPhoto_url() {
        return photo_url;
    }
}
