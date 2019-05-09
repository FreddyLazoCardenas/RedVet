package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.DoctorEntity;
import com.papps.freddy_lazo.domain.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class SearchDoctorsMapper {

    public SearchDoctorsMapper() {
    }

    public static List<Doctor> transform(List<DoctorEntity> entity) {
        List<Doctor> doctorList = new ArrayList<>();
        if (entity == null) {
            return doctorList;
        }

        for (DoctorEntity doctorEntity : entity) {
            doctorList.add(transform(doctorEntity));
        }
        return doctorList;
    }

    public static Doctor transform(DoctorEntity entity) {
        return new Doctor(entity.getAttentions(), entity.getRate(), entity.getUser_id(), entity.getType_document(), entity.getNumber_document(), entity.getBusiness_name(), entity.getAddress(), entity.getLatitude(), entity.getLongitude(), entity.getConsultation_price(), entity.getConsultation_time(), entity.getShower_price(), entity.getShower_time(), entity.getPhone(), entity.getType(), entity.getTuition_number()
                , entity.getDescription(), entity.getAttention(), entity.getAvailable(), entity.getFirst_name(), entity.getLast_name(), entity.getEmail(), entity.getPhoto_url(), entity.getApi_token(), PetMapper.transform(entity.getPetEntity()), ScheduleMapper.transform(entity.getSchedules()), ServiceDoctorMapper.transform(entity.getServices()));
    }
}
