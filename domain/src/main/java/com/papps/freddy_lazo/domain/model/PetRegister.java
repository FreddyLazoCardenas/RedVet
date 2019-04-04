package com.papps.freddy_lazo.domain.model;

public class PetRegister {

    private int pet_id;
    private String name;
    private String birthday;
    private String breed;
    private String photo;

    public PetRegister(int pet_id, String name, String birthday, String breed, String photo) {
        this.pet_id = pet_id;
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.photo = photo;
    }

    public PetRegister(int pet_id) {
        this.pet_id = pet_id;
    }
}
