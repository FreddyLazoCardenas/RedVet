package com.papps.freddy_lazo.redvet.model;

public class ScheduleRegisterModel {

    private int day;
    private String dayName;
    private String startHour;
    private String endHour;
    private boolean isCheck;
    private boolean isSelected;

    public ScheduleRegisterModel(int day, String dayName, String startHour, String endHour) {
        this.day = day;
        this.dayName = dayName;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
