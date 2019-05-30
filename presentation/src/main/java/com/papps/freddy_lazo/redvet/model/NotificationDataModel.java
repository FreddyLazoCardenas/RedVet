package com.papps.freddy_lazo.redvet.model;

public class NotificationDataModel {

    private String type;
    private int appointmentId;
    private String message;

    public NotificationDataModel(String type, int appointmentId, String message) {
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
