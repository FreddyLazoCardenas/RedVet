package com.papps.freddy_lazo.redvet.view.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.iid.FirebaseInstanceId;
import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerProfileFragmentComponent;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.presenter.ProfileFragmentPresenter;
import com.papps.freddy_lazo.redvet.presenter.RegisterFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.adapter.PetProfileAdapter;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CameraDialog;
import com.papps.freddy_lazo.redvet.interfaces.ProfileFragmentView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment implements CameraDialog.OnClickListener, ProfileFragmentView {


    private static final String PICTURE_FILE_NAME = "profileComplete.jpg";
    private static final String PICTURE_CROPPED_FILE_NAME = "profile.jpg";

    private static final int SELECT_FILE = 1;
    private static final int REQUEST_CAMERA = 0;

    @Inject
    PreferencesManager preferencesManager;
    @Inject
    ProfileFragmentPresenter presenter;
    @Inject
    PetProfileAdapter adapter;

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.et_dni)
    EditText etDni;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_repeat_password)
    EditText etRepeatPassword;
    @BindView(R.id.img_profile)
    ImageView imgProfile;

    @BindView(R.id.til_name)
    TextInputLayout tilName;
    @BindView(R.id.til_last_name)
    TextInputLayout tilLastName;
    @BindView(R.id.rv_pet)
    RecyclerView rvPets;


    private HomeActivity activity;
    private PetLoverModel petLoverModel;
    private PetRegister petLoverRegisterModel;
    private File pictureFile;
    private File croppedProfileFile;
    private File croppedPetFile;

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
        petLoverModel = PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser());
        fillUi();
    }

    private void fillUi() {
        etName.setText(petLoverModel.getFirst_name());
        etLastName.setText(petLoverModel.getLast_name());
        etDni.setText(petLoverModel.getDni());
        etAddress.setText(petLoverModel.getAddress());
        etEmail.setText(petLoverModel.getEmail());
        etPhone.setText(petLoverModel.getPhone());
        displayPhoto(true, true);
        setUpRv();
    }

    private void setUpRv() {
        rvPets.setLayoutManager(new LinearLayoutManager(activity));
        rvPets.setAdapter(adapter);
        adapter.bindList(petLoverModel.getPetList());
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (HomeActivity) getActivity();
        initUI();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @OnClick(R.id.img_profile)
    public void imgProfile() {
        navigator.showListDialog(activity, this);
    }

    @Override
    public Context context() {
        return activity;
    }

    @Override
    public void showErrorMessage(String message) {
        showMessage(activity, message);
    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    @OnClick(R.id.btn_log_out)
    public void logOut() {
        preferencesManager.savePetLoverCurrentUser("");
        navigator.navigateToLoginActivity(activity);
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
        requestPermissions(new String[]{Manifest.permission.CAMERA}, RegisterFragmentPresenter.PERMISSION_REQUEST_CAMERA_CODE);
    }

    @Override
    public void requestGalleryPermission() {
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RegisterFragmentPresenter.PERMISSION_REQUEST_GALLERY_CODE);
    }

    @Override
    public String getName() {
        return etName.getText().toString();
    }

    @Override
    public String getLastName() {
        return etLastName.getText().toString();
    }

    @Override
    public String getDni() {
        return etDni.getText().toString();
    }

    @Override
    public String getAddress() {
        return etAddress.getText().toString();
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public String getPhone() {
        return etPhone.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public String getRepeatPassword() {
        return etRepeatPassword.getText().toString();
    }

    @Override
    public String getApiToken() {
        return petLoverModel.getApi_token();
    }

    @Override
    public void showLastNameError(String message) {
        showError(tilLastName, etLastName, message);
    }

    @Override
    public void hideLastNameError() {
        hideError(tilLastName);
    }

    @Override
    public void showNameError(String message) {
        showError(tilName, etName, message);
    }

    @Override
    public void hideNameError() {
        hideError(tilName);
    }

    @Override
    public void showAddressError(String message) {
        showEtError(etAddress, message);
    }

    @Override
    public void hideAddressError() {
        hideEtError(etAddress);
    }

    @Override
    public void showEmailError(String message) {
        showEtError(etEmail, message);
    }

    @Override
    public void hideEmailError() {
        hideEtError(etEmail);
    }

    @Override
    public void showDniError(String message) {
        showEtError(etDni, message);
    }

    @Override
    public void hideDniError() {
        hideEtError(etDni);
    }

    @Override
    public void showRepeatPasswordError(String message) {
        showEtError(etRepeatPassword, message);
    }

    @Override
    public void hideRepeatPasswordError() {
        hideEtError(etRepeatPassword);
    }

    @Override
    public void showPasswordError(String message) {
        showEtError(etPassword, message);
    }

    @Override
    public void hidePasswordError() {
        hideEtError(etPassword);
    }

    @Override
    public void showPhoneError(String message) {
        showEtError(etPhone, message);
    }

    @Override
    public void hidePhoneError() {
        hideEtError(etPhone);
    }

    @Override
    public String getPetName() {
        return null;
        //return etPetName.getText().toString();
    }

    @Override
    public String getPetBirthday() {
        return null;
        //return etPetBirthday.getText().toString();
    }

    @Override
    public String getPetBreed() {
        return null;
        // return etpetBreed.getText().toString();
    }

    @Override
    public void showPetNameError(String message) {
        // showEtError(etPetName, message);
    }

    @Override
    public void hidePetNameError() {
        // hideEtError(etPetName);
    }

    @Override
    public void showPetBirthdayError(String message) {
        //showEtError(etPetBirthday, message);
    }

    @Override
    public void hidePetBirthdayError() {
        // hideEtError(etPetBirthday);
    }

    @Override
    public void showPetBreedError(String message) {
        //  showEtError(etpetBreed, message);
    }

    @Override
    public void hidePetBreedError() {
        // hideEtError(etpetBreed);
    }

    @Override
    public String getToken() {
        return FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    public void savePetData() {
        // petLoverRegisterModel = new PetRegister(1, getPetName(), getPetBirthday(), getPetBreed(), getPetBase64Image());
    }

  /*  @Override
    public String getPetBase64Image() {
        if (croppedPetFile != null) {
            Bitmap bm = BitmapFactory.decodeFile(croppedPetFile.getAbsolutePath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b, Base64.DEFAULT);
        } else
            return "";
    }*/

    @Override
    public String getProfileBase64Image() {
        if (croppedProfileFile != null) {
            Bitmap bm = BitmapFactory.decodeFile(croppedProfileFile.getAbsolutePath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b, Base64.NO_WRAP);
        } else
            return (petLoverModel.getPhoto_url() != null && petLoverModel.getPhoto_url().equals("")) ? null : petLoverModel.getPhoto_url();
    }

    @Override
    public ArrayList<PetRegister> getPetData() {
        List<PetLoverRegisterModel> petData = petLoverModel.getPetList();
        ArrayList<PetRegister> petRegisters = new ArrayList<>();
        for (PetLoverRegisterModel petLoverRegisterModel : petData) {
            String photo = petLoverRegisterModel.getPhoto();
            petRegisters.add(new PetRegister(petLoverRegisterModel.getId(), petLoverRegisterModel.getPet_id(), petLoverRegisterModel.getName(), petLoverRegisterModel.getBirthday(), petLoverRegisterModel.getBreed(), (photo != null && petLoverRegisterModel.getPhoto().equals("")) ? null : petLoverRegisterModel.getPhoto()));
        }
        return petRegisters;
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


    public void displayPhoto(boolean refresh, boolean fromModel) {
        GlideApp.with(activity)
                .asBitmap()
                .dontAnimate()
                .diskCacheStrategy(refresh ? DiskCacheStrategy.NONE : DiskCacheStrategy.ALL)
                .skipMemoryCache(refresh)
                .placeholder(R.drawable.ic_placeholder)
                .load(fromModel ? petLoverModel.getPhoto_url() : croppedProfileFile.getAbsolutePath())
                .into(imgProfile);
    }

    @OnClick(R.id.btn_update)
    public void btnUpdate() {
        presenter.validation();
    }

    private void showError(TextInputLayout inputLayout, EditText editText, String message) {
        inputLayout.setErrorEnabled(true);
        inputLayout.setError(message);
        editText.requestFocus();
    }

    private void hideError(TextInputLayout inputLayout) {
        if (inputLayout.isErrorEnabled()) {
            inputLayout.setError(null);
            inputLayout.setErrorEnabled(false);
        }
    }

    private void showEtError(EditText editText, String message) {
        editText.setError(message);
        editText.requestFocus();
    }

    private void hideEtError(EditText editText) {
        editText.setError(null);
    }


}
