package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.redvet.interfaces.BaseView;

import java.util.ArrayList;

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

    String getProfileBase64Image();

    void savePetData();

    void showPetNameError(String string);

    void hidePetNameError();

    void showPetBirthdayError(String string);

    void hidePetBirthdayError();

    void showPetBreedError(String string);

    void hidePetBreedError();
}
