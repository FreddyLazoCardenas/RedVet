package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.AppointmentEntity;
import com.papps.freddy_lazo.domain.model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentMapper {

    public AppointmentMapper() {
    }

    public static List<Appointment> transform(List<AppointmentEntity> data) {
        List<Appointment> appointmentList = new ArrayList<>();
        if (data == null) {
            return appointmentList;
        }

        for (AppointmentEntity appointmentEntity : data) {
            appointmentList.add(transform(appointmentEntity));
        }
        return appointmentList;
    }

    public static Appointment transform(AppointmentEntity data) {
        return new Appointment(data.getId(), data.getDoctor_id(), data.getPet_lover_id(), data.getPet_by_pet_lover_id(), data.getDate(), data.getTime(), data.getType(), data.getReason(), data.getStatus(), data.getStatus_reason(), data.getQualification(), data.getDiagnosis(), SearchDoctorsMapper.transform(data.getDoctor()));
    }
}
