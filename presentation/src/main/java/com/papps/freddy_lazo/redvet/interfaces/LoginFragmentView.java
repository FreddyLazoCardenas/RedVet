package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.redvet.model.DoctorModel;

public interface LoginFragmentView extends BaseView {

    String getEmail();

    String getFlavor();

    String getPassword();

    void showEmailError(String string);

    void hideEmailError();

    void showPasswordError(String string);

    void hidePasswordError();

    void successLogin();

    void loginDoctorResponse(DoctorModel transform);

    String getFcmToken();
}
