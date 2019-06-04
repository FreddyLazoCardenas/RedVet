package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.redvet.model.AppointmentPhotoModel;

public interface DiagnoseAppointmentView extends BaseView {
    String getDiagnose();

    String getApiToken();

    int getAppointmentId();

    void successRequest();

    void requestCameraPermission();

    void requestGalleryPermission(int docType);

    int getAppointmentPhotoId();

    void successDelete();

    void successPhotoUpload(AppointmentPhotoModel data);
}
