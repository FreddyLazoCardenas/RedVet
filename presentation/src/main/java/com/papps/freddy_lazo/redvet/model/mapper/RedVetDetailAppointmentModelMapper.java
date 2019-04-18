package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.RedVetDetailAppointment;
import com.papps.freddy_lazo.redvet.model.RedVetDetailAppointmentModel;

import java.util.ArrayList;
import java.util.List;

public class RedVetDetailAppointmentModelMapper {

    public RedVetDetailAppointmentModelMapper() {
    }

    public static RedVetDetailAppointmentModel transform(RedVetDetailAppointment data) {
        if (data == null) {
            return null;
        }
        return new RedVetDetailAppointmentModel(data.getId(),data.getDoctor_id(),data.getPet_lover_id(),data.getPet_by_pet_lover_id(),data.getDate(),data.getTime(),data.getType(),data.getReason(),data.getStatus(),data.getStatus_reason(),data.getQualification(),data.getDiagnosis(),DoctorModelMapper.transform(data.getDoctor()),AppointmentPhotoModelMapper.transform(data.getPhotos()),PetLoverModelMapper.transform(data.getPet_lover()), PetModelMapper.transform(data.getPet()));
    }

    public static List<RedVetDetailAppointmentModel> transform(List<RedVetDetailAppointment> data) {
        List<RedVetDetailAppointmentModel> list = new ArrayList<>();
        if (data == null) {
            return list;
        }
        for (RedVetDetailAppointment redVetDetailAppointment : data){
            list.add(transform(redVetDetailAppointment));
        }
        return list;
    }
}
