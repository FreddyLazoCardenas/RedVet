package com.papps.freddy_lazo.redvet.presenter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.RegisterFragmentView;

import javax.inject.Inject;

public class RegisterFragmentPresenter implements Presenter<RegisterFragmentView> {


    public static final int PERMISSION_REQUEST_CAMERA_CODE = 4;
    public static final int PERMISSION_REQUEST_GALLERY_CODE = 5;

    private RegisterFragmentView view;

    @Inject
    public RegisterFragmentPresenter() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

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
        if (!isValidName(view.getName()))
            return;
        if (!isValidLastName(view.getLastName()))
            return;
        if (!isValidDni(view.getDni()))
            return;
        if (!isValidAddress(view.getAddress()))
            return;
        if (!isValidEmail(view.getEmail()))
            return;
        if (!isValidPhone(view.getPhone()))
            return;
        if (!isValidPassword(view.getPassword()))
            return;
        if (!isValidRepeatPassword(view.getRepeatPassword(), view.getPassword()))
            return;
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

    private boolean isValidDni(String dni) {
        if (TextUtils.isEmpty(dni)) {
            view.showDniError(view.context().getString(R.string.text_required_field));
            return false;
        }
        if (dni.length() != 8) {
            view.showDniError(view.context().getString(R.string.text_bad_format_field));
            return false;
        }
        view.hideDniError();
        return true;
    }


    private boolean isValidRepeatPassword(String repeatPassword, String password) {
        if (TextUtils.isEmpty(repeatPassword)) {
            view.showRepeatPasswordError(view.context().getString(R.string.text_required_field));
            return false;
        }
        if (repeatPassword.length() < 6) {
            view.showRepeatPasswordError(view.context().getString(R.string.text_bad_format_field));
            return false;
        }

        if (!repeatPassword.equals(password)) {
            view.showRepeatPasswordError(view.context().getString(R.string.text_not_match));
            return false;
        }
        view.hideRepeatPasswordError();
        return true;
    }

    private boolean isValidPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            view.showPasswordError(view.context().getString(R.string.text_required_field));
            return false;
        }
        if (password.length() < 6) {
            view.showPasswordError(view.context().getString(R.string.text_bad_format_field));
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
}