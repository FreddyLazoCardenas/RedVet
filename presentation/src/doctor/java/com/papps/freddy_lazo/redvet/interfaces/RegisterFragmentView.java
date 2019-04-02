package com.papps.freddy_lazo.redvet.interfaces;


public interface RegisterFragmentView extends BaseView {

    void requestCameraPermission();

    void requestGalleryPermission();

    String getNumber();

    String getBusinessName();

    String getName();

    String getLastName();

    String getAddress();

    String getEmail();

    String getPhone();

    String getPassword();

    String getJob();

    String getTuition();

    void showDocumentNumberError(String message);

    void showBusinessNameError(String message);

    void showNameError(String message);

    void showLastNameError(String message);

    void showAddressError(String message);

    void showEmailError(String message);

    void showPhoneError(String message);

    void showPasswordError(String message);

    void showJobError(String message);

    void showTuitionError(String message);

    void hideDocumentNumberError();

    void hideBusinessNameError();

    void hideNameError();

    void hideLastNameError();

    void hideAddressError();

    void hideEmailError();

    void hidePhoneError();

    void hidePasswordError();

    void hideJobError();

    void hideTuitionError();
}
