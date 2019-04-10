package com.papps.freddy_lazo.redvet.interfaces;

public interface DiagnoseAppointmentView extends BaseView {
    String getDiagnose();

    String getApiToken();

    int getAppointmentId();

    void successRequest();

    void requestCameraPermission();

    void requestGalleryPermission();
}
