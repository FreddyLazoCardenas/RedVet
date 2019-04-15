package com.papps.freddy_lazo.domain.model;

public class PetRegister {

    private Integer id;
    private Integer pet_id;
    private String doctor_id;
    private String name;
    private String birthday;
    private String breed;
    private String photo;
    private String photo_url;

    public PetRegister(Integer id, int pet_id, String doctor_id, String name, String birthday, String breed, String photo) {
        this.id = id;
        this.pet_id = pet_id;
        this.doctor_id = doctor_id;
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.photo = photo;
    }

    public PetRegister(Integer id, int pet_id, String name, String birthday, String breed, String photo) {
        this.id = id;
        this.pet_id = pet_id;
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.photo = photo;
    }

    public PetRegister(Integer pet_id, String petName, String petBirthday, String petBreed, String petBase64Image) {
        this.pet_id = pet_id;
        this.name = petName;
        this.birthday = petBirthday;
        this.breed = petBreed;
        this.photo = petBase64Image;
    }

    public PetRegister(int id) {
        this.id = id;
    }

    public PetRegister(int id, int pet_id) {
        this.id = id;
        this.pet_id = pet_id;
    }

    public Integer getId() {
        return id;
    }

    public int getPet_id() {
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

    public String getPhoto_url() {
        return photo_url;
    }
}
