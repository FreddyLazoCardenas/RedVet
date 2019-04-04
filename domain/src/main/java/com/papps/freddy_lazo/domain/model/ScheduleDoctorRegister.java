package com.papps.freddy_lazo.domain.model;

public class ScheduleDoctorRegister {

    private int day;
    private String start_time;
    private String end_time;

    public ScheduleDoctorRegister(int day, String start_time, String end_time) {
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
    }
}
