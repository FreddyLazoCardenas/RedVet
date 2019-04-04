package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.Pet;
import com.papps.freddy_lazo.domain.model.Service;
import com.papps.freddy_lazo.redvet.model.NewsModel;
import com.papps.freddy_lazo.redvet.model.PetModel;
import com.papps.freddy_lazo.redvet.model.ServicesModel;

import java.util.ArrayList;
import java.util.List;

public class PetModelMapper {

    public PetModelMapper() {
    }

    public static List<PetModel> transform(List<Pet> pet) {
        List<PetModel> petModels = new ArrayList<>();
        if (pet == null)
            return petModels;

        for (Pet petModel : pet) {
            petModels.add(transform(petModel));
        }
        return petModels;
    }

    public static PetModel transform(Pet pet) {
        return new PetModel(pet.getId(), pet.getDoctor_id(), pet.getPet_id());
    }

    public static PetModel transformCustomer(Pet pet) {
        return new PetModel(pet.getId(), pet.getDoctor_id(), pet.getPet_id());
    }
}
