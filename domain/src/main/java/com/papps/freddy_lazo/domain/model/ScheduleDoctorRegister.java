package com.papps.freddy_lazo.domain.model;

public class ScheduleDoctorRegister {

    private Integer id;
    private Integer day;
    private String start_time;
    private String end_time;

    public ScheduleDoctorRegister(Integer day, String start_time, String end_time) {
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public ScheduleDoctorRegister(Integer id, Integer day, String start_time, String end_time) {
        this.id = id;
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
    }
}
