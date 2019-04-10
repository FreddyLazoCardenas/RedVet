package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.AppointmentPhotoEntity;
import com.papps.freddy_lazo.domain.model.AppointmentPhoto;

public class AppointmentPhotoMapper {

    public static AppointmentPhoto transform(AppointmentPhotoEntity data){
        return new AppointmentPhoto();
    }
}
