package com.papps.freddy_lazo.redvet.presenter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import com.papps.freddy_lazo.data.exception.RedVetException;
import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorSignUp;
import com.papps.freddy_lazo.domain.interactor.PetRedVetUseCase;
import com.papps.freddy_lazo.domain.model.PetRedVet;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.RegisterFragmentView;
import com.papps.freddy_lazo.redvet.model.mapper.PetRedVetModelMapper;

import java.util.List;

import javax.inject.Inject;

public class RegisterFragmentPresenter implements Presenter<RegisterFragmentView> {


    public static final int PERMISSION_REQUEST_CAMERA_CODE = 4;
    public static final int PERMISSION_REQUEST_GALLERY_CODE = 5;
    private final DoctorSignUp doctorSignUp;
    private final PetRedVetUseCase petRedVetUseCase;


    private RegisterFragmentView view;

    @Inject
    public RegisterFragmentPresenter(DoctorSignUp doctorSignUp, PetRedVetUseCase petRedVetUseCase) {
        this.doctorSignUp = doctorSignUp;
        this.petRedVetUseCase = petRedVetUseCase;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        doctorSignUp.unsubscribe();
        petRedVetUseCase.unsubscribe();
    }

    public void getPets() {
        petRedVetUseCase.execute(new PetsObservable());
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

    @Override
    public void setView(RegisterFragmentView view) {
        this.view = view;
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

    public void validateData() {
        if (!isValidNumber(view.getNumber()))
            return;
        if (!isValidName(view.getName()))
            return;
        if (!isValidLastName(view.getLastName()))
            return;
        if (!isValidAddress(view.getAddress()))
            return;
        if (!isValidEmail(view.getEmail()))
            return;
        if (!isValidPhone(view.getPhone()))
            return;
        if (!isValidPassword(view.getPassword()))
            return;
        if (!isValidPets(view.getPets()))
            return;
        if (!isValidServices(view.getServices()))
            return;
        if (!isValidSchedules(view.getSchedules()))
            return;
        if (!isValidConsultationPrice(view.getConsultationPriceVisibility()))
            return;
        if (!isValidShowerPrice(view.getShowerPriceVisibility()))
            return;

        doctorSignUp.bindParams(view.getEmail(), view.getPassword(), view.getName(), view.getLastName(), view.getTypeDocument(), view.getNumber(), view.getBusinessName()
                , view.getAddress(), view.getLatitude(), view.getLongitude(), view.getConsultationPrice(), view.getConsultationTime(), view.getShowerPrice(), view.getShowerTime()
                , view.getTuition(), view.getDescription(), view.getPhone(), view.getProfileBase64Image(), view.getType(), view.getAttention(), view.getFcmToken()
                , "android", view.getPets(), view.getSchedules(), view.getServices());
        doctorSignUp.execute(new DoctorSignUpObservable());
    }

    private boolean isValidSchedules(List<ScheduleDoctorRegister> schedules) {
        if (schedules != null && !schedules.isEmpty()) {
            return true;
        } else {
            view.showErrorMessage(view.context().getString(R.string.add_schedules_data));
            return false;
        }
    }

    private boolean isValidServices(List<ServicesDoctorRegister> services) {
        if (services != null && !services.isEmpty()) {
            return true;
        } else {
            view.showErrorMessage(view.context().getString(R.string.add_services_data));
            return false;
        }
    }

    private boolean isValidPets(List<PetRegister> pets) {
        if (pets != null && !pets.isEmpty()) {
            return true;
        } else {
            view.showErrorMessage(view.context().getString(R.string.add_pets_data));
            return false;
        }
    }

    private boolean isValidConsultationPrice(int consultationPriceVisibility) {
        if (consultationPriceVisibility == View.VISIBLE) {
            if (view.getConsultationPrice().isEmpty()) {
                view.showConsultationPriceError(view.context().getString(R.string.text_required_field));
                return false;
            } else {
                view.hideConsultationPriceError();
                return true;
            }
        } else {
            view.hideConsultationPriceError();
            return true;
        }
    }

    private boolean isValidShowerPrice(int showerPriceVisibility) {
        if (showerPriceVisibility == View.VISIBLE) {
            if (view.getShowerPrice().isEmpty()) {
                view.showShowerPriceError(view.context().getString(R.string.text_required_field));
                return false;
            } else {
                view.hideShowerPriceError();
                return true;
            }
        } else {
            view.hideShowerPriceError();
            return true;
        }
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

    private boolean isValidAddress(String address) {
        if (TextUtils.isEmpty(address)) {
            view.showAddressError(view.context().getString(R.string.text_required_field));
            return false;
        }
        view.hideAddressError();
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

    private boolean isValidNumber(String number) {
        if (TextUtils.isEmpty(number) && view.getTypeDocument().equals("dni")) {
            view.showDocumentNumberError(view.context().getString(R.string.text_required_field));
            return false;
        }
        view.hideDocumentNumberError();
        return true;
    }

    private class DoctorSignUpObservable extends DefaultObserver<List<Void>> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            super.onError(e);
            RedVetException exception = (RedVetException) e;
            if (e.getMessage().contains("enviado"))
                view.successRegisterRequest();
            else
                view.showErrorMessage(exception.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
