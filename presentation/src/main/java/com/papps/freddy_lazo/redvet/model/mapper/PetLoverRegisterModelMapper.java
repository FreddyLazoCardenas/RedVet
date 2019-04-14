package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;

public class PetLoverRegisterModelMapper {

    public PetLoverRegisterModelMapper() {
    }

    public static PetLoverRegisterModel transform(PetRegister petRegister) {
        return new PetLoverRegisterModel(petRegister.getId(),petRegister.getPet_id(),petRegister.getDoctor_id(),petRegister.getName(),petRegister.getBirthday(),petRegister.getBreed(),petRegister.getPhoto(),petRegister.getPhoto_url());
    }
}
