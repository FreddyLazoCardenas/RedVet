package com.papps.freddy_lazo.domain.model;

public class RedVetMessage {

    private int id;
    private String appointment_id;
    private String message;
    private String from;

    public RedVetMessage(int id, String appointment_id, String message, String from) {
        this.id = id;
        this.appointment_id = appointment_id;
        this.message = message;
        this.from = from;
    }

    public int getId() {
        return id;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return from;
    }
}
