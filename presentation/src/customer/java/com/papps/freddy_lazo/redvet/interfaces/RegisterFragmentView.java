package com.papps.freddy_lazo.redvet.interfaces;


public interface RegisterFragmentView extends BaseView {

    void requestCameraPermission();

    void requestGalleryPermission();

    String getProfileBase64Image();

    String getPetBase64Image();

    String getDeviceId();

    String getName();

    String getLastName();

    String getDni();

    String getAddress();

    String getEmail();

    String getPhone();

    String getPassword();

    String getRepeatPassword();

    void showLastNameError(String message);

    void hideLastNameError();

    void showNameError(String message);

    void hideNameError();

    void showAddressError(String message);

    void hideAddressError();

    void showEmailError(String message);

    void hideEmailError();

    void showDniError(String message);

    void hideDniError();

    void showRepeatPasswordError(String message);

    void hideRepeatPasswordError();

    void showPasswordError(String message);

    void hidePasswordError();

    void showPhoneError(String message);

    void hidePhoneError();
}
