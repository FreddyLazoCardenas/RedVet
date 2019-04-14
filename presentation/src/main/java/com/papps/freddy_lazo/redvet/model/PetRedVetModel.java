package com.papps.freddy_lazo.redvet.model;

public class PetRedVetModel {

    private int id;
    private String name;
    private String photo_url;
    private String available;
    private boolean isSelected;

    public PetRedVetModel(int id, String name, String photo_url, String available) {
        this.id = id;
        this.name = name;
        this.photo_url = photo_url;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public String getAvailable() {
        return available;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
