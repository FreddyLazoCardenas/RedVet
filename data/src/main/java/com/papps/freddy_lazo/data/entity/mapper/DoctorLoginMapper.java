package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.DoctorEntity;
import com.papps.freddy_lazo.domain.model.Doctor;

public class DoctorLoginMapper {

    public DoctorLoginMapper() {
    }

    public static Doctor transform(DoctorEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Doctor(entity.getUser_id(),entity.getType_document(),entity.getNumber_document(),entity.getBusiness_name(),entity.getAddress(),entity.getLatitude(),entity.getLongitude(),entity.getConsultation_price(),entity.getPhone(),entity.getType(),entity.getTuition_number()
                ,entity.getDescription(),entity.getAttention(),entity.getAvailable(),entity.getFirst_name(),entity.getLast_name(),entity.getEmail(),entity.getPhoto_url(),entity.getApi_token(),PetMapper.transform(entity.getPetEntity()), ScheduleMapper.transform(entity.getSchedules()),ServiceDoctorMapper.transform(entity.getServices()));
    }
}
