package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.PetLoverEntity;
import com.papps.freddy_lazo.domain.model.PetLover;

public class PetLoverLoginMapper {

    public PetLoverLoginMapper() {
    }

    public static PetLover transform(PetLoverEntity entity) {
        if (entity == null) {
            return null;
        }
        return new PetLover(entity.getUser_id(), entity.getDni(), entity.getAddress(),
                entity.getPhone(), entity.getFirst_name(), entity.getLast_name(), entity.getPhoto_url(), entity.getEmail(), entity.getApi_token(), PetMapper.transform(entity.getPetEntity()));
    }
}
