package com.papps.freddy_lazo.domain.model;

public class Pet {

    private int id;
    private String doctor_id;
    private String pet_id;

    public Pet(int id, String doctor_id, String pet_id) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.pet_id = pet_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getPet_id() {
        return pet_id;
    }

    public void setPet_id(String pet_id) {
        this.pet_id = pet_id;
    }
}
