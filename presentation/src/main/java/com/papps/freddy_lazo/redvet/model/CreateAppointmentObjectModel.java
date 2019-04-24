package com.papps.freddy_lazo.redvet.model;

public class CreateAppointmentObjectModel {

    private String name;
    private String searchName;
    private boolean selected;

    public CreateAppointmentObjectModel(String name) {
        this.name = name;
    }

    public CreateAppointmentObjectModel(String name, String searchName) {
        this.name = name;
        this.searchName = searchName;
    }

    public CreateAppointmentObjectModel(String name, String searchName, boolean selected) {
        this.name = name;
        this.searchName = searchName;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
}
