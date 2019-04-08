package com.papps.freddy_lazo.redvet.model;

public class CreateAppointmentObjectModel {

    private String name;
    private boolean selected;

    public CreateAppointmentObjectModel(String name) {
        this.name = name;
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
}
