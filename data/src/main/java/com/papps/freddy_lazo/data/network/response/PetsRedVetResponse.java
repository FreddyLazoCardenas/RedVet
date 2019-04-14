package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.PetRedVetEntity;

import java.util.List;

public class PetsRedVetResponse {

    @SerializedName("pets")
    List<PetRedVetEntity> pets;

    public List<PetRedVetEntity> getPets() {
        return pets;
    }
}
