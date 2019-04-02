package com.papps.freddy_lazo.redvet.model;

public class ServicesModel {

    private int id;
    private String name;
    private String available;
    private boolean state;

    public ServicesModel(int id, String name, String available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
