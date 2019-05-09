package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.PetLover;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;

public class PetLoverModelMapper {

    public PetLoverModelMapper() {
    }

    public static PetLoverModel transform(PetLover petLover) {
        return new PetLoverModel(petLover.getAttentions(), petLover.getUser_id(), petLover.getDni(), petLover.getAddress(), petLover.getPhone(), petLover.getFirst_name(), petLover.getLast_name(), petLover.getPhoto_url(), petLover.getEmail(), petLover.getApi_token(), PetModelMapper.transform(petLover.getPetList()));
    }
}
