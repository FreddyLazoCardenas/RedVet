package com.papps.freddy_lazo.redvet.model;


public class PetLoverRegisterModel {

    private int id;
    private int pet_id;
    private String doctor_id;
    private String name;
    private String birthday;
    private String breed;
    private String photo;
    private boolean isSelected;

    public PetLoverRegisterModel(int id, int pet_id, String doctor_id, String name, String birthday, String breed, String photo) {
        this.id = id;
        this.pet_id = pet_id;
        this.doctor_id = doctor_id;
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

    public int getPet_id() {
        return pet_id;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
