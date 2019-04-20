package com.papps.freddy_lazo.redvet.interfaces;

public interface DoctorNotificationFinishedView extends BaseView {
    String getApiToken();

    int getAppointmentId();

    int getQualification();

    void successRequest();
}
