package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.PetRedVet;
import com.papps.freddy_lazo.redvet.model.PetRedVetModel;

import java.util.ArrayList;
import java.util.List;

public class PetRedVetModelMapper {

    public PetRedVetModelMapper() {
    }

    public static PetRedVetModel transform(PetRedVet data) {
        if (data == null) {
            return null;
        }
        return new PetRedVetModel(data.getId(),data.getName(),data.getPhoto_url(),data.getAvailable());
    }

    public static List<PetRedVetModel> transform(List<PetRedVet> data) {
        List<PetRedVetModel> list = new ArrayList<>();
        if (data == null) {
            return list;
        }
        for (PetRedVet petRedVet : data){
            list.add(transform(petRedVet));
        }
        return list;
    }
}
