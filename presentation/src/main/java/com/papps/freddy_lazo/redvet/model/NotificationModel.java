package com.papps.freddy_lazo.redvet.model;

public class NotificationModel {

    private Integer id;
    private String type;
    private String appointment_id;
    private String message;
    private String time;
    private boolean from_doctor;

    public NotificationModel(Integer id, String type, String appointment_id, String message, String time, boolean from_doctor) {
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
