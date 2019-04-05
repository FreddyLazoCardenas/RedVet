package com.papps.freddy_lazo.redvet.view.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.redvet.BuildConfig;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.ProfileFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerProfileFragmentComponent;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.presenter.ProfileFragmentPresenter;
import com.papps.freddy_lazo.redvet.presenter.RegisterFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CameraDialog;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment implements CameraDialog.OnClickListener, ProfileFragmentView {


    public static final int PERMISSION_REQUEST_CAMERA_CODE = 4;
    public static final int PERMISSION_REQUEST_GALLERY_CODE = 5;
    private static final String PICTURE_FILE_NAME = "profileComplete.jpg";
    private static final String PICTURE_CROPPED_FILE_NAME = "profile.jpg";

    private static final int SELECT_FILE = 1;
    private static final int REQUEST_CAMERA = 0;

    @Inject
    PreferencesManager preferencesManager;
    @Inject
    ProfileFragmentPresenter presenter;

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.img_profile)
    ImageView imgProfile;

    @BindView(R.id.til_name)
    TextInputLayout tilName;
    @BindView(R.id.til_last_name)
    TextInputLayout tilLastName;

    private File pictureFile;
    private HomeActivity activity;
    private DoctorModel doctorModel;
    private File croppedProfileFile;


    public static Fragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void initUI() {
        presenter.setView(this);
        getUserData();
    }

    private void getUserData() {
        doctorModel = DoctorModel.toModel(preferencesManager.getDoctorCurrentUser());
        fillUi();
    }


    private void fillUi() {
        etName.setText(doctorModel.getFirst_name());
        etLastName.setText(doctorModel.getLast_name());
        etNumber.setText(doctorModel.getNumber_document());
        etAddress.setText(doctorModel.getAddress());
        etEmail.setText(doctorModel.getEmail());
        etPhone.setText(doctorModel.getPhone());
        displayPhoto(true, true);
       // setUpRv();
    }

    private void displayPhoto(boolean refresh, boolean fromModel) {
        GlideApp.with(activity)
                .asBitmap()
                .dontAnimate()
                .diskCacheStrategy(refresh ? DiskCacheStrategy.NONE : DiskCacheStrategy.ALL)
                .skipMemoryCache(refresh)
                .placeholder(R.drawable.ic_placeholder)
                .load(fromModel ? doctorModel.getPhoto_url() : croppedProfileFile.getAbsolutePath())
                .into(imgProfile);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    private void buildInjection() {
        DaggerProfileFragmentComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (HomeActivity) getActivity();
        initUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_FILE && resultCode == Activity.RESULT_OK) {
            // selectGalleryButton();
            startCrop(data.getData());
        } else if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            //selectSelfieButton();
            startCrop(Uri.fromFile(pictureFile));
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                displayPhoto(true, false);
            } else {
                croppedProfileFile = null;
                // unSelectButtons();
            }
        }
    }

    private void startCrop(Uri source) {
        croppedProfileFile = new File(getContext().getFilesDir(), PICTURE_CROPPED_FILE_NAME);
        CropImage.activity(source)
                .setCropShape(CropImageView.CropShape.OVAL)
                .setFixAspectRatio(true)
                .setBorderCornerThickness(0)
                .setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                .setOutputCompressQuality(50)
                .setMaxCropResultSize(1800, 1800)
                .setOutputUri(Uri.fromFile(croppedProfileFile))
                .start(getContext(), this);
    }

    @Override
    public Context context() {
        return activity;
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    @Override
    public void camera() {
        if (presenter.checkCameraHardware() && presenter.checkPermissions()) {
            if (pictureFile == null) {
                pictureFile = new File(getContext().getFilesDir(), PICTURE_FILE_NAME);
            }
            navigator.navigateToTakePictureCamera(this, pictureFile, REQUEST_CAMERA);
        }
    }

    @Override
    public void gallery() {
        if (presenter.checkGalleryPermissions()) {
            navigator.navigateToGallery(this, SELECT_FILE);
        }
    }

    @Override
    public void requestCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA_CODE);
    }

    @Override
    public void requestGalleryPermission() {
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_GALLERY_CODE);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public String getDni() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPhone() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getRepeatPassword() {
        return null;
    }

    @Override
    public ArrayList<PetRegister> getPetData() {
        return null;
    }

    @Override
    public void showLastNameError(String string) {

    }

    @Override
    public void hideLastNameError() {

    }

    @Override
    public void showNameError(String string) {

    }

    @Override
    public void hideNameError() {

    }

    @Override
    public void showAddressError(String string) {

    }

    @Override
    public void hideAddressError() {

    }

    @Override
    public void showEmailError(String string) {

    }

    @Override
    public void hideEmailError() {

    }

    @Override
    public void showDniError(String string) {

    }

    @Override
    public void hideDniError() {

    }

    @Override
    public void showRepeatPasswordError(String string) {

    }

    @Override
    public void hideRepeatPasswordError() {

    }

    @Override
    public void showPasswordError(String string) {

    }

    @Override
    public void hidePasswordError() {

    }

    @Override
    public void showPhoneError(String string) {

    }

    @Override
    public void hidePhoneError() {

    }

    @Override
    public String getPetName() {
        return null;
    }

    @Override
    public String getPetBirthday() {
        return null;
    }

    @Override
    public String getPetBreed() {
        return null;
    }

    @Override
    public String getProfileBase64Image() {
        return null;
    }

    @Override
    public void savePetData() {

    }

    @Override
    public void showPetNameError(String string) {

    }

    @Override
    public void hidePetNameError() {

    }

    @Override
    public void showPetBirthdayError(String string) {

    }

    @Override
    public void hidePetBirthdayError() {

    }

    @Override
    public void showPetBreedError(String string) {

    }

    @Override
    public void hidePetBreedError() {

    }

    @OnClick(R.id.btn_update)
    public void btnUpdate() {
        presenter.validation();
    }


    @OnClick(R.id.btn_log_out)
    public void logOut() {
        preferencesManager.saveDoctorCurrentUser("");
        navigator.navigateToLoginActivity(activity);
    }

    @OnClick(R.id.img_profile)
    public void imgProfile() {
        navigator.showListDialog(activity, this);
    }
}
