package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.PetEntity;
import com.papps.freddy_lazo.domain.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetMapper {

    public PetMapper() {
    }

    static List<Pet> transform(List<PetEntity> entity) {
        List<Pet> entities = new ArrayList<>();
        if (entity == null) {
            return entities;
        }
        for (PetEntity pet : entity){
            entities.add(transform(pet));
        }
        return entities;
    }

    private static Pet transform(PetEntity entity){
        return new Pet(entity.getId(),entity.getDoctor_id(),entity.getPet_id(),entity.getName(),entity.getBirthday(),entity.getBreed(),entity.getPhoto());
    }

}
