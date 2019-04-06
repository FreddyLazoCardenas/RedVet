package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.redvet.model.DoctorModel;

import java.util.ArrayList;
import java.util.List;

public class DoctorModelMapper {

    public DoctorModelMapper() {
    }

    public static List<DoctorModel> transform(List<Doctor> doctorList) {
        List<DoctorModel> doctorModelList = new ArrayList<>();
        if (doctorList == null)
            return doctorModelList;
        for (Doctor doctor : doctorList) {
            doctorModelList.add(transform(doctor));
        }
        return doctorModelList;
    }

    public static DoctorModel transform(Doctor doctor) {
        return new DoctorModel(doctor.getUser_id(), doctor.getType_document(), doctor.getNumber_document(), doctor.getBusiness_name(), doctor.getAddress(), doctor.getLatitude(), doctor.getLongitude(), doctor.getConsultation_price(), doctor.getConsultation_time(), doctor.getShower_price(), doctor.getShower_time(), doctor.getPhone(), doctor.getType(), doctor.getTuition_number()
                , doctor.getDescription(), doctor.getAttention(), doctor.getAvailable(), doctor.getFirst_name(), doctor.getLast_name(), doctor.getEmail(), doctor.getPhoto_url(), doctor.getApi_token(), PetModelMapper.transform(doctor.getPetList()), ScheduleMapper.transform(doctor.getScheduleList()), ServiceDoctorMapper.transform(doctor.getServiceList()));
    }
}
