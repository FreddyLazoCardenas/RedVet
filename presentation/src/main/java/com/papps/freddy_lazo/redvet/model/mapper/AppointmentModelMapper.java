package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.Appointment;
import com.papps.freddy_lazo.redvet.model.AppointmentModel;

public class AppointmentModelMapper {

    public AppointmentModelMapper() {
    }

    public static AppointmentModel transform(Appointment data) {
        if (data == null) {
            return null;
        }
        return new AppointmentModel(data.getId(),data.getDoctor_id(),data.getPet_lover_id(),data.getPet_by_pet_lover_id(),data.getDate(),data.getTime(),data.getType(),data.getReason(),data.getStatus(),data.getStatus_reason(),data.getQualification(),data.getDiagnosis(),DoctorModelMapper.transform(data.getDoctor()));
    }
}
