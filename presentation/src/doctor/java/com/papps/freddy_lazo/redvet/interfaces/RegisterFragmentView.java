package com.papps.freddy_lazo.redvet.interfaces;


import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;
import com.papps.freddy_lazo.redvet.model.NewsModel;
import com.papps.freddy_lazo.redvet.model.PetRedVetModel;

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

    String getTuition();

    void showDocumentNumberError(String message);
    
    void showNameError(String message);

    void showLastNameError(String message);

    void showAddressError(String message);

    void showEmailError(String message);

    void showPhoneError(String message);

    void showPasswordError(String message);

    void hideDocumentNumberError();
    
    void hideNameError();

    void hideLastNameError();

    void hideAddressError();

    void hideEmailError();

    void hidePhoneError();

    void hidePasswordError();

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

    int getConsultationPriceVisibility();

    int getShowerPriceVisibility();

    void showConsultationPriceError(String message);

    void hideConsultationPriceError();

    void showShowerPriceError(String string);

    void hideShowerPriceError();

    void successRequest(List<PetRedVetModel> transform);
}
