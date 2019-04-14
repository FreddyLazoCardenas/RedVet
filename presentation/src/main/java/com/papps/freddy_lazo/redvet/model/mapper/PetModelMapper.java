package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.Pet;
import com.papps.freddy_lazo.domain.model.Service;
import com.papps.freddy_lazo.redvet.model.NewsModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.model.PetModel;
import com.papps.freddy_lazo.redvet.model.ServicesModel;

import java.util.ArrayList;
import java.util.List;

public class PetModelMapper {

    public PetModelMapper() {
    }

    public static List<PetLoverRegisterModel> transform(List<Pet> pet) {
        List<PetLoverRegisterModel> petModels = new ArrayList<>();
        if (pet == null)
            return petModels;

        for (Pet petModel : pet) {
            petModels.add(transform(petModel));
        }
        return petModels;
    }

    public static PetLoverRegisterModel transform(Pet pet) {
        return new PetLoverRegisterModel(pet.getId(),pet.getPet_id(),pet.getDoctor_id(),pet.getName(),pet.getBirthday(),pet.getBreed(),pet.getPhoto(),pet.getPhoto_url());
    }

}
