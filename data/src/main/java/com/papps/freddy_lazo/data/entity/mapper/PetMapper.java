package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.PetEntity;
import com.papps.freddy_lazo.domain.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetMapper {

    public PetMapper() {
    }

    static List<Pet> transform(List<PetEntity> entity) {
        if (entity == null) {
            return null;
        }
        List<Pet> entities = new ArrayList<>();
        for (PetEntity pet : entity){
            entities.add(transform(pet));
        }
        return entities;
    }

    private static Pet transform(PetEntity entity){
        return new Pet(entity.getId(),entity.getDoctor_id(),entity.getPet_id());
    }
}
