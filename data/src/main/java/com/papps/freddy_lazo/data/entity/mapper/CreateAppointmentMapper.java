package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.CreateAppointmentEntity;
import com.papps.freddy_lazo.domain.model.CreateAppointment;

public class CreateAppointmentMapper {

    public CreateAppointmentMapper() {
    }

    public static CreateAppointment transform(CreateAppointmentEntity data) {
        return new CreateAppointment(data.getId(), data.getDoctor_id(), data.getPet_lover_id(), data.getPet_by_pet_lover_id(), data.getDate(), data.getTime(), data.getType(), data.getReason());
    }
}
