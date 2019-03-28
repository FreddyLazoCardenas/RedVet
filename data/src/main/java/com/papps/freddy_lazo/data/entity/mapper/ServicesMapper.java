package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.PetEntity;
import com.papps.freddy_lazo.data.entity.ServicesEntity;
import com.papps.freddy_lazo.domain.model.Pet;
import com.papps.freddy_lazo.domain.model.Service;

import java.util.ArrayList;
import java.util.List;

public class ServicesMapper {

    public ServicesMapper() {
    }

    public static List<Service> transform(List<ServicesEntity> entity) {
        if (entity == null) {
            return null;
        }
        List<Service> entities = new ArrayList<>();
        for (ServicesEntity service : entity){
            entities.add(transform(service));
        }
        return entities;
    }

    private static Service transform(ServicesEntity entity){
        return new Service(entity.getId(),entity.getName(),entity.getAvailable());
    }
}
