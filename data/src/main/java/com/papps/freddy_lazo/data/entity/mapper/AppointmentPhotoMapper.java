package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.AppointmentPhotoEntity;
import com.papps.freddy_lazo.domain.model.AppointmentPhoto;

import java.util.ArrayList;
import java.util.List;

public class AppointmentPhotoMapper {

    public static List<AppointmentPhoto> transform(List<AppointmentPhotoEntity> data){
        List<AppointmentPhoto> list = new ArrayList<>();
        if(data == null){
            return  list;
        }

        for(AppointmentPhotoEntity appointmentPhotoEntity : data){
            list.add(transform(appointmentPhotoEntity));
        }
        return list;
    }

    public static AppointmentPhoto transform(AppointmentPhotoEntity data){
        return new AppointmentPhoto(data.getId(),data.getAppointment_id(),data.getPhoto_url());
    }
}
