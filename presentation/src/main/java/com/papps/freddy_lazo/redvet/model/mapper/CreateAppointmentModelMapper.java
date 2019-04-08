package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.Appointment;
import com.papps.freddy_lazo.domain.model.CreateAppointment;
import com.papps.freddy_lazo.redvet.model.AppointmentModel;
import com.papps.freddy_lazo.redvet.model.CreateAppointmentModel;

public class CreateAppointmentModelMapper {

    public CreateAppointmentModelMapper() {
    }

    public static CreateAppointmentModel transform(CreateAppointment data) {
        if (data == null) {
            return null;
        }
        return new CreateAppointmentModel(data.getId(),data.getDoctor_id(),data.getPet_lover_id(),data.getPet_by_pet_lover_id(),data.getDate(),data.getTime(),data.getType(),data.getReason());
    }
}
