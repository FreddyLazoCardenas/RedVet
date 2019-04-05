package com.papps.freddy_lazo.domain.model;

public class PetRegister {

    private int id;
    private String pet_id;
    private String doctor_id;
    private String name;
    private String birthday;
    private String breed;
    private String photo;

    public PetRegister(int id, String pet_id, String doctor_id, String name, String birthday, String breed, String photo) {
        this.id = id;
        this.pet_id = pet_id;
        this.doctor_id = doctor_id;
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.photo = photo;
    }

    public PetRegister(int id, String petName, String petBirthday, String petBreed, String petBase64Image) {
        this.id = id;
        this.name = petName;
        this.birthday = petBirthday;
        this.breed = petBase64Image;
        this.photo = petBreed;
    }

    public PetRegister(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPet_id() {
        return pet_id;
    }

    public String getDoctor_id() {
        return doctor_id;
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
}
