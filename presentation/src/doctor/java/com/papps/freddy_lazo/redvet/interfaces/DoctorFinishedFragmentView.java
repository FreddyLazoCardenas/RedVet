package com.papps.freddy_lazo.redvet.interfaces;

public interface DoctorFinishedFragmentView extends BaseView {
    String getApiToken();

    int getAppointmentId();

    int getPhotoId();

    void successRequest();
}
