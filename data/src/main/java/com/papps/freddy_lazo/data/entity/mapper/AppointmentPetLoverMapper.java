package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.PetLoverAppointmentEntity;
import com.papps.freddy_lazo.domain.model.PetLoverAppointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentPetLoverMapper {

    public AppointmentPetLoverMapper() {
    }

    public static List<PetLoverAppointment> transform(List<PetLoverAppointmentEntity> data) {
        List<PetLoverAppointment> appointmentList = new ArrayList<>();
        if (data == null) {
            return appointmentList;
        }

        for (PetLoverAppointmentEntity petLoverAppointmentEntity : data) {
            appointmentList.add(transform(petLoverAppointmentEntity));
        }
        return appointmentList;
    }

    public static PetLoverAppointment transform(PetLoverAppointmentEntity data) {
        return new PetLoverAppointment(data.getId(), data.getDoctor_id(), data.getPet_lover_id(), data.getPet_by_pet_lover_id(), data.getDate(), data.getTime(), data.getType(), data.getReason(), data.getStatus(), data.getStatus_reason(), data.getQualification(), data.getDiagnosis(), SearchDoctorsMapper.transform(data.getDoctor()),AppointmentPhotoMapper.transform(data.getPhotos()));
    }
}
