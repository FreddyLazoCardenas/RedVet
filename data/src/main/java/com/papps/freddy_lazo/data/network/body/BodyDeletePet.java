package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;

public class BodyDeletePet {

    @SerializedName("pet_by_pet_lover_id")
    private int petLoverId;

    public BodyDeletePet(int petLoverId) {
        this.petLoverId = petLoverId;
    }
}
