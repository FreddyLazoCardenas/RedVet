package com.papps.freddy_lazo.redvet.interfaces;

public interface PetLoverConfirmedView extends BaseView {
    String getApiToken();

    String getReason();

    int getAppointmentId();

    void showReasonError(String message);

    void hideReasonError();

    void successRequest();
}
