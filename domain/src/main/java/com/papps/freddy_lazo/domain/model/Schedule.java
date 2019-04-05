package com.papps.freddy_lazo.domain.model;

public class Schedule {

    private int id;
    private int day;
    private String start_time;
    private String end_time;

    public Schedule(int id, int day, String start_time, String end_time) {
        this.id = id;
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public int getId() {
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
