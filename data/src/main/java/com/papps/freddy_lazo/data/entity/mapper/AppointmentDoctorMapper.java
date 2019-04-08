package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.DoctorAppointmentEntity;
import com.papps.freddy_lazo.data.entity.PetLoverAppointmentEntity;
import com.papps.freddy_lazo.domain.model.DoctorAppointment;
import com.papps.freddy_lazo.domain.model.PetLoverAppointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDoctorMapper {

    public AppointmentDoctorMapper() {
    }

    public static List<DoctorAppointment> transform(List<DoctorAppointmentEntity> data) {
        List<DoctorAppointment> appointmentList = new ArrayList<>();
        if (data == null) {
            return appointmentList;
        }

        for (DoctorAppointmentEntity doctorAppointment : data) {
            appointmentList.add(transform(doctorAppointment));
        }
        return appointmentList;
    }

    public static DoctorAppointment transform(DoctorAppointmentEntity data) {
        return new DoctorAppointment(data.getId(), data.getDoctor_id(), data.getPet_lover_id(), data.getPet_by_pet_lover_id(), data.getDate(), data.getTime(), data.getType(), data.getReason(), data.getStatus(), data.getStatus_reason(), data.getQualification(), data.getDiagnosis(), PetLoverLoginMapper.transform(data.getDoctor()),PetMapper.transform(data.getPet()));
    }
}
