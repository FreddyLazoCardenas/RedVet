package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;
import com.papps.freddy_lazo.redvet.interfaces.BaseView;

import java.util.ArrayList;
import java.util.List;

public interface ProfileFragmentView extends BaseView {

    void requestCameraPermission();

    void requestGalleryPermission();

    String getName();

    String getLastName();

    String getNumber();

    String getAddress();

    String getEmail();

    String getPhone();

    String getPassword();

    String getApiToken();

    String getTypeDocument();

    String getType();

    String getAttention();

    String getBusinessName();

    String getLatitude();

    String getLongitude();

    ArrayList<PetRegister> getPetData();

    void showLastNameError(String string);

    void hideLastNameError();

    void showNameError(String string);

    void hideNameError();

    void showAddressError(String string);

    void hideAddressError();

    void showEmailError(String string);

    void hideEmailError();

    void showDocumentNumber(String string);

    void hideDocumentNumber();

    void showPasswordError(String string);

    void hidePasswordError();

    void showPhoneError(String string);

    void hidePhoneError();

    String getPetName();

    String getPetBirthday();

    String getPetBreed();

    String getTuition();

    String getFcmToken();

    List<ScheduleDoctorRegister> getSchedules();

    List<ServicesDoctorRegister> getServices();

    String getProfileBase64Image();

    void savePetData();

    void showPetNameError(String string);

    void hidePetNameError();

    void showPetBirthdayError(String string);

    void hidePetBirthdayError();

    void showPetBreedError(String string);

    void hidePetBreedError();

    int getConsultationPriceVisibility();

    int getShowerPriceVisibility();

    void showConsultationPriceError(String message);

    void hideConsultationPriceError();

    void showShowerPriceError(String string);

    void hideShowerPriceError();

    String getConsultationPrice();

    String getConsultationTime();

    String getShowerPrice();

    String getShowerTime();

    String getDescription();

    void updateView();
}
