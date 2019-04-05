package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.ServicesDoctorEntity;
import com.papps.freddy_lazo.domain.model.ServiceDoctor;

import java.util.ArrayList;
import java.util.List;

public class ServiceDoctorMapper {
    public static List<ServiceDoctor> transform(List<ServicesDoctorEntity> services) {
        List<ServiceDoctor> serviceDoctors = new ArrayList<>();
        if (services == null)
            return serviceDoctors;
        for (ServicesDoctorEntity servicesDoctorEntity : services) {
            serviceDoctors.add(transform(servicesDoctorEntity));
        }
        return serviceDoctors;
    }

    private static ServiceDoctor transform(ServicesDoctorEntity servicesDoctorEntity) {
        return new ServiceDoctor(servicesDoctorEntity.getId(), servicesDoctorEntity.getService_id());
    }
}
