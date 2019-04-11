package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.AppointmentPhoto;
import com.papps.freddy_lazo.redvet.model.AppointmentPhotoModel;

import java.util.ArrayList;
import java.util.List;

public class AppointmentPhotoModelMapper {

    public static List<AppointmentPhotoModel> transform(List<AppointmentPhoto> data){
        List<AppointmentPhotoModel> list = new ArrayList<>();
        if(data == null){
            return  list;
        }

        for(AppointmentPhoto appointmentPhotoEntity : data){
            list.add(transform(appointmentPhotoEntity));
        }
        return list;
    }


    public static AppointmentPhotoModel transform(AppointmentPhoto data){
        return new AppointmentPhotoModel(data.getId(),data.getAppointment_id(),data.getPhoto_url());
    }
}
