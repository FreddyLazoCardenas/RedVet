package com.papps.freddy_lazo.redvet.model;


public class PetLoverRegisterModel {

    private int pet_id;
    private String name;
    private String birthday;
    private String breed;
    private String photo;

    public PetLoverRegisterModel(int pet_id, String name, String birthday, String breed, String photo) {
        this.pet_id = pet_id;
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.photo = photo;
    }

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
