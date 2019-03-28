package com.papps.freddy_lazo.redvet.model.mapper;

import android.se.omapi.SEService;

import com.papps.freddy_lazo.domain.model.Service;
import com.papps.freddy_lazo.redvet.model.ServicesModel;

import java.util.ArrayList;
import java.util.List;

public class ServicesModelMapper {

    public ServicesModelMapper() {
    }

    public static List<ServicesModel> transform(List<Service> serviceList) {
        List<ServicesModel> listData = new ArrayList<>();
        if (serviceList == null)
            return listData;
        for (Service service : serviceList) {
            listData.add(transform(service));
        }
        return listData;
    }

    private static ServicesModel transform(Service service) {
        return new ServicesModel(service.getId(), service.getName(), service.getAvailable());
    }
}
