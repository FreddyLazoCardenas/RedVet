package com.papps.freddy_lazo.redvet.interfaces;


import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;
import com.papps.freddy_lazo.redvet.model.NewsModel;

import java.util.List;

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

    String getTypeDocument();

    String getLatitude();

    String getLongitude();

    String getConsultationPrice();

    String getConsultationTime();

    String getShowerPrice();

    String getShowerTime();

    String getDescription();

    String getType();

    String getAttention();

    String getFcmToken();

    List<PetRegister> getPets();

    List<ScheduleDoctorRegister> getSchedules();

    List<ServicesDoctorRegister> getServices();

    String getProfileBase64Image();
}
