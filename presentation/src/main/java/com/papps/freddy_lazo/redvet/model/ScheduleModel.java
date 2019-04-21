package com.papps.freddy_lazo.redvet.model;

public class ScheduleModel {

    private Integer id;
    private int day;
    private String start_time;
    private String end_time;

    public ScheduleModel(Integer id, int day, String start_time, String end_time) {
        this.id = id;
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Integer getId() {
        return id;
    }

    public int getDay() {
        return day;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }
}
