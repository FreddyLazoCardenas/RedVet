package com.papps.freddy_lazo.redvet.presenter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorUpdate;
import com.papps.freddy_lazo.domain.interactor.PetRedVetUseCase;
import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.domain.model.PetRedVet;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.ProfileFragmentView;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.model.mapper.DoctorModelMapper;
import com.papps.freddy_lazo.redvet.model.mapper.PetRedVetModelMapper;

import java.util.List;

import javax.inject.Inject;


public class ProfileFragmentPresenter implements Presenter<ProfileFragmentView> {

    private static final int PERMISSION_REQUEST_CAMERA_CODE = 4;
    private static final int PERMISSION_REQUEST_GALLERY_CODE = 5;
    private final DoctorUpdate doctorUpdate;
    private final PreferencesManager preferencesManager;
    private final PetRedVetUseCase petRedVetUseCase;
    private ProfileFragmentView view;

    @Inject
    public ProfileFragmentPresenter(PreferencesManager preferencesManager , DoctorUpdate doctorUpdate, PetRedVetUseCase petRedVetUseCase) {
        this.doctorUpdate = doctorUpdate;
        this.petRedVetUseCase = petRedVetUseCase;
        this.preferencesManager = preferencesManager;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        doctorUpdate.unsubscribe();
    }

    public void getPets() {
        petRedVetUseCase.execute(new PetsObservable());
    }

    @Override
    public void setView(ProfileFragmentView view) {
        this.view = view;
    }

    private class PetsObservable extends DefaultObserver<List<PetRedVet>> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(List<PetRedVet> petRedVets) {
            super.onNext(petRedVets);
            view.successRequest(PetRedVetModelMapper.transform(petRedVets));
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }


    public boolean checkCameraHardware() {
        return view.context().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(view.context(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(PERMISSION_REQUEST_CAMERA_CODE);
            return false;
        }
        return true;
    }

    private void requestPermissions(int permissionCode) {
        if (permissionCode == PERMISSION_REQUEST_CAMERA_CODE) {
            view.requestCameraPermission();
        } else if (permissionCode == PERMISSION_REQUEST_GALLERY_CODE) {
            view.requestGalleryPermission();
        }
    }

    public boolean checkGalleryPermissions() {
        if (ContextCompat.checkSelfPermission(view.context(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(PERMISSION_REQUEST_GALLERY_CODE);
            return false;
        }
        return true;
    }

    public void validation() {
        if (!isValidName(view.getName()))
            return;
        if (!isValidLastName(view.getLastName()))
            return;
        if (!isValidDocNumber(view.getNumber()))
            return;
        if (!isValidAddress(view.getAddress()))
            return;
        if (!isValidEmail(view.getEmail()))
            return;
        if (!isValidPhone(view.getPhone()))
            return;
        /*if (!isValidPassword(view.getPassword()))
            return;*/
        if (!validatePetModelData())
            return;
        doctorUpdate.bindParams(view.getApiToken(), view.getEmail(), view.getPassword(), view.getName(), view.getLastName(), view.getTypeDocument(), view.getNumber(), view.getBusinessName(), view.getAddress(), view.getLatitude(),
                view.getLongitude(), view.getConsultationPrice(), view.getConsultationTime(), view.getShowerPrice(), view.getShowerTime(), view.getTuition(), view.getDescription(), view.getPhone(), view.getProfileBase64Image(),
                view.getType(), view.getAttention(), view.getFcmToken(), "android", view.getPetData(), view.getSchedules(), view.getServices());
        doctorUpdate.execute(new DoctorUpdateObservable());
    }

    private boolean validatePetModelData() {
        if (view.getPetData() == null) {
            view.showErrorMessage(view.context().getString(R.string.add_pet_data));
            return false;
        }
        return true;
    }

    private boolean isValidLastName(String lastName) {
        if (TextUtils.isEmpty(lastName)) {
            view.showLastNameError(view.context().getString(R.string.text_required_field));
            return false;
        }
        view.hideLastNameError();
        return true;
    }

    private boolean isValidName(String name) {
        if (TextUtils.isEmpty(name)) {
            view.showNameError(view.context().getString(R.string.text_required_field));
            return false;
        }
        view.hideNameError();
        return true;
    }

    private boolean isValidAddress(String address) {
        if (TextUtils.isEmpty(address)) {
            view.showAddressError(view.context().getString(R.string.text_required_field));
            return false;
        }
        view.hideAddressError();
        return true;
    }

    private boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            view.showEmailError(view.context().getString(R.string.text_required_field));
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showEmailError(view.context().getString(R.string.text_bad_format_field));
            return false;
        }
        view.hideEmailError();
        return true;
    }

    private boolean isValidDocNumber(String docNumber) {
        if (TextUtils.isEmpty(docNumber)) {
            view.showDocumentNumber(view.context().getString(R.string.text_required_field));
            return false;
        }
       /* if (docNumber.length() != 8) {
            view.showDocumentNumber(view.context().getString(R.string.text_bad_format_field));
            return false;
        }*/
        view.hideDocumentNumber();
        return true;
    }

    private boolean isValidPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            view.showPasswordError(view.context().getString(R.string.text_required_field));
            return false;
        }
        if (password.length() < 6) {
            view.showPasswordError(view.context().getString(R.string.text_password_bad_format_field));
            return false;
        }
        view.hidePasswordError();
        return true;
    }


    private boolean isValidPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            view.showPhoneError(view.context().getString(R.string.text_required_field));
            return false;
        }
        if (phone.length() < 9) {
            view.showPhoneError(view.context().getString(R.string.text_bad_format_field));
            return false;
        }
        view.hidePhoneError();
        return true;
    }

    public void validatePetData() {
        if (!isValidPetName(view.getPetName()))
            return;
        if (!isValidPetBirthday(view.getPetBirthday()))
            return;
        if (!isValidPetBreed(view.getPetBreed()))
            return;
        view.savePetData();
    }

    private boolean isValidPetName(String name) {
        if (TextUtils.isEmpty(name)) {
            view.showPetNameError(view.context().getString(R.string.text_required_field));
            return false;
        }
        view.hidePetNameError();
        return true;
    }

    private boolean isValidPetBirthday(String petBirthday) {
        if (TextUtils.isEmpty(petBirthday)) {
            view.showPetBirthdayError(view.context().getString(R.string.text_required_field));
            return false;
        }
        view.hidePetBirthdayError();
        return true;
    }

    private boolean isValidPetBreed(String breed) {
        if (TextUtils.isEmpty(breed)) {
            view.showPetBreedError(view.context().getString(R.string.text_required_field));
            return false;
        }
        view.hidePetBreedError();
        return true;
    }

    private class DoctorUpdateObservable extends DefaultObserver<Doctor> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(Doctor doctor) {
            super.onNext(doctor);
            DoctorModel doctorModel = DoctorModelMapper.transform(doctor);
            preferencesManager.saveDoctorCurrentUser(doctorModel.toString());
            view.showErrorMessage("Éxito");
            view.updateView();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
