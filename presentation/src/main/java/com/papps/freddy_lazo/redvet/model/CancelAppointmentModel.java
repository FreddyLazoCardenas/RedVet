package com.papps.freddy_lazo.redvet.model;

public class CancelAppointmentModel {

    private String text;
    private boolean selected;

    public CancelAppointmentModel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
