package com.papps.freddy_lazo.domain.model;

public class NotificationData {

    private String type;
    private int appointmentId;
    private String message;

    public NotificationData(String type, int appointmentId, String message) {
        this.type = type;
        this.appointmentId = appointmentId;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getMessage() {
        return message;
    }
}
