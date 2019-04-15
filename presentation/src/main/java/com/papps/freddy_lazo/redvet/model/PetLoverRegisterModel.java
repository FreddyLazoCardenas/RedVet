package com.papps.freddy_lazo.redvet.model;


public class PetLoverRegisterModel {

    private Integer id;
    private int pet_id;
    private String doctor_id;
    private String name;
    private String birthday;
    private String breed;
    private String photo;
    private String photo_url;
    private boolean isSelected;

    public PetLoverRegisterModel(Integer id, int pet_id, String doctor_id, String name, String birthday, String breed, String photo,String photo_url) {
        this.id = id;
        this.pet_id = pet_id;
        this.doctor_id = doctor_id;
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.photo = photo;
        this.photo_url = photo_url;
    }

    public Integer getId() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
