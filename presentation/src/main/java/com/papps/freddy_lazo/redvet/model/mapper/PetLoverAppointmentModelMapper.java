package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.PetLoverAppointment;
import com.papps.freddy_lazo.redvet.model.PetLoverAppointmentModel;

import java.util.ArrayList;
import java.util.List;

public class PetLoverAppointmentModelMapper {

    public PetLoverAppointmentModelMapper() {
    }

    public static PetLoverAppointmentModel transform(PetLoverAppointment data) {
        if (data == null) {
            return null;
        }
        return new PetLoverAppointmentModel(data.getId(),data.getDoctor_id(),data.getPet_lover_id(),data.getPet_by_pet_lover_id(),data.getDate(),data.getTime(),data.getType(),data.getReason(),data.getStatus(),data.getStatus_reason(),data.getQualification(),data.getDiagnosis(),DoctorModelMapper.transform(data.getDoctor()));
    }

    public static List<PetLoverAppointmentModel> transform(List<PetLoverAppointment> data) {
        List<PetLoverAppointmentModel> list = new ArrayList<>();
        if (data == null) {
            return list;
        }
        for (PetLoverAppointment appointment : data){
            list.add(transform(appointment));
        }
        return list;
    }
}
