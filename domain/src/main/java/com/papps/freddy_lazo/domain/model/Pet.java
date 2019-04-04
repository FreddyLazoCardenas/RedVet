package com.papps.freddy_lazo.domain.model;

public class Pet {

    private int id;
    private String doctor_id;
    private String pet_id;
    private String name;
    private String birthday;
    private String breed;
    private String photo;

    public Pet(int id, String doctor_id, String pet_id) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.pet_id = pet_id;
    }

    public Pet(int id, String pet_id, String name, String birthday, String breed, String photo) {
        this.id = id;
        this.pet_id = pet_id;
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public String getPet_id() {
        return pet_id;
    }
}
