package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.RedVetDetailAppointmentEntity;
import com.papps.freddy_lazo.domain.model.RedVetDetailAppointment;

public class RedVetAppointmentDetailMapper {

    public RedVetAppointmentDetailMapper() {
    }

    public static RedVetDetailAppointment transform(RedVetDetailAppointmentEntity data) {
        return new RedVetDetailAppointment(data.getId(), data.getDoctor_id(), data.getPet_lover_id(), data.getPet_by_pet_lover_id(), data.getDate(), data.getTime(), data.getType(), data.getReason(), data.getStatus(), data.getStatus_reason(), data.getQualification(), data.getDiagnosis(), DoctorLoginMapper.transform(data.getDoctor()), AppointmentPhotoMapper.transform(data.getPhotos()), PetLoverLoginMapper.transform(data.getPet_lover()), PetMapper.transform(data.getPet()));
    }
}
