package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.DoctorAppointment;
import com.papps.freddy_lazo.redvet.model.DoctorAppointmentModel;

import java.util.ArrayList;
import java.util.List;

public class DoctorAppointmentModelMapper {

    public DoctorAppointmentModelMapper() {
    }

    public static DoctorAppointmentModel transform(DoctorAppointment data) {
        if (data == null) {
            return null;
        }
        return new DoctorAppointmentModel(data.getId(),data.getDoctor_id(),data.getPet_lover_id(),data.getPet_by_pet_lover_id(),data.getDate(),data.getTime(),data.getType(),data.getReason(),data.getStatus(),data.getStatus_reason(),data.getQualification(),data.getDiagnosis(),PetLoverModelMapper.transform(data.getPetLover()), PetModelMapper.transform(data.getPets()));
    }

    public static List<DoctorAppointmentModel> transform(List<DoctorAppointment> data) {
        List<DoctorAppointmentModel> list = new ArrayList<>();
        if (data == null) {
            return list;
        }
        for (DoctorAppointment appointment : data){
            list.add(transform(appointment));
        }
        return list;
    }
}
