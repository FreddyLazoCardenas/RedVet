package com.papps.freddy_lazo.data.entity;

public class PetEntity {

    private int id;
    private String doctor_id;
    private String pet_id;
    private String name;
    private String birthday;
    private String breed;
    private String photo;
    private String photo_url;

    public PetEntity(int id, String doctor_id, String pet_id) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.pet_id = pet_id;
    }

    public PetEntity(int id, String doctor_id, String pet_id, String name, String birthday, String breed, String photo, String photo_url) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.pet_id = pet_id;
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.photo = photo;
        this.photo_url = photo_url;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBreed() {
        return breed;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPhoto_url() {
        return photo_url;
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
