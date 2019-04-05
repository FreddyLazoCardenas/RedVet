package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.data.entity.ServicesDoctorEntity;
import com.papps.freddy_lazo.domain.model.ServiceDoctor;
import com.papps.freddy_lazo.redvet.model.ServiceDoctorModel;

import java.util.ArrayList;
import java.util.List;

public class ServiceDoctorMapper {

    public ServiceDoctorMapper() {
    }

    public static List<ServiceDoctorModel> transform(List<ServiceDoctor> data) {
        List<ServiceDoctorModel> serviceDoctorList = new ArrayList<>();
        if (data == null) {
            return serviceDoctorList;
        }
        for (ServiceDoctor servicesDoctorEntity : data ){
            serviceDoctorList.add(transform(servicesDoctorEntity));
        }
        return serviceDoctorList;
    }

    private static ServiceDoctorModel transform(ServiceDoctor entity) {
        return new ServiceDoctorModel(entity.getId(), entity.getService_id());
    }
}
