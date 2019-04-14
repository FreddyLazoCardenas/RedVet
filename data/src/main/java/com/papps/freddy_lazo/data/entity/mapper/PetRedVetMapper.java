package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.PetRedVetEntity;
import com.papps.freddy_lazo.domain.model.PetRedVet;

import java.util.ArrayList;
import java.util.List;

public class PetRedVetMapper {

    public PetRedVetMapper() {
    }

    public static List<PetRedVet> transform(List<PetRedVetEntity> data) {
        List<PetRedVet> redVetList = new ArrayList<>();
        if (data == null) {
            return redVetList;
        }

        for (PetRedVetEntity petRedVetEntity : data) {
            redVetList.add(transform(petRedVetEntity));
        }
        return redVetList;
    }

    public static PetRedVet transform(PetRedVetEntity data) {
        return new PetRedVet(data.getId(), data.getName(), data.getPhoto_url(), data.getAvailable());
    }
}
