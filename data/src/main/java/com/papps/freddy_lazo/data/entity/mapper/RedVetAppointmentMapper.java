package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.RedVetAppointmentEntity;
import com.papps.freddy_lazo.domain.model.RedVetAppointment;

public class RedVetAppointmentMapper {

    public RedVetAppointmentMapper() {
    }


    public static RedVetAppointment transform(RedVetAppointmentEntity data) {
        return new RedVetAppointment(data.getId(), data.getDoctor_id(), data.getPet_lover_id(), data.getPet_by_pet_lover_id(), data.getDate(), data.getTime(), data.getType(), data.getReason(), data.getStatus(), data.getStatus_reason(), data.getQualification(), data.getDiagnosis());
    }
}
