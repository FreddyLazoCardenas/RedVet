package com.papps.freddy_lazo.redvet.view.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.RegisterFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerRegisterFragmentComponent;
import com.papps.freddy_lazo.redvet.model.PetRedVetModel;
import com.papps.freddy_lazo.redvet.presenter.RegisterFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.RegisterActivity;
import com.papps.freddy_lazo.redvet.view.adapter.AddPetAdapter;
import com.papps.freddy_lazo.redvet.view.adapter.PetAdapter;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CameraDialog;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment implements RegisterFragmentView, CameraDialog.OnClickListener ,
        DatePickerDialog.OnDateSetListener,AddPetAdapter.onClickAdapter{


    private static final String PICTURE_FILE_NAME = "profileComplete.jpg";
    private static final String PICTURE_CROPPED_FILE_NAME = "profile.jpg";

    private static final int SELECT_FILE = 1;
    private static final int REQUEST_CAMERA = 0;

    @Inject
    AddPetAdapter adapter;
    @Inject
    RegisterFragmentPresenter presenter;

    @BindView(R.id.rv_pet)
    RecyclerView recyclerView;
    @BindView(R.id.img_register)
    ImageView imgRegister;
    @BindView(R.id.img_add)
    ImageView imgPet;

    @BindView(R.id.til_name)
    TextInputLayout tilName;
    @BindView(R.id.til_last_name)
    TextInputLayout tilLastName;

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
    @BindView(R.id.et_rpt_password)
    EditText etRptPassword;
    @BindView(R.id.pet_name)
    EditText etPetName;
    @BindView(R.id.pet_birthday)
    EditText etPetBirthday;
    @BindView(R.id.pet_breed)
    EditText etpetBreed;

    private RegisterActivity activity;
    private File pictureFile;
    private File croppedProfileFile;
    private File croppedPetFile;
    private boolean isPetImage;
    private PetRegister petLoverModel;

    public static Fragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    private void buildInjection() {
        DaggerRegisterFragmentComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (RegisterActivity) getActivity();
        FirebaseApp.initializeApp(activity);
        initUI();
    }

    @Override
    public void initUI() {
        presenter.setView(this);
        setUpPetRv();
        presenter.getPets();
    }

    private void setUpPetRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setView(this);
    }


    @OnClick(R.id.btn_register)
    public void btnRegister() {
        presenter.validateData();
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    @OnClick(R.id.btn_pet_save)
    public void addPet() {
        presenter.validatePetData();
    }

    @OnClick(R.id.img_register)
    public void imgRegister() {
        isPetImage = false;
        navigator.showListDialog(activity, this);
    }

    @OnClick(R.id.img_add)
    public void imgPet() {
        isPetImage = true;
        navigator.showListDialog(activity, this);
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
        return etRptPassword.getText().toString();
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
        showEtError(etRptPassword, message);
    }

    @Override
    public void hideRepeatPasswordError() {
        hideEtError(etRptPassword);
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
        return etPetName.getText().toString();
    }

    @Override
    public String getPetBirthday() {
        return etPetBirthday.getText().toString();
    }

    @Override
    public String getPetBreed() {
        return etpetBreed.getText().toString();
    }

    @Override
    public void showPetNameError(String message) {
        showEtError(etPetName, message);
    }

    @Override
    public void hidePetNameError() {
        hideEtError(etPetName);
    }

    @Override
    public void showPetBirthdayError(String message) {
        showEtError(etPetBirthday, message);
    }

    @Override
    public void hidePetBirthdayError() {
        hideEtError(etPetBirthday);
    }

    @Override
    public void showPetBreedError(String message) {
        showEtError(etpetBreed, message);
    }

    @Override
    public void hidePetBreedError() {
        hideEtError(etpetBreed);
    }

    @Override
    public void savePetData() {
        petLoverModel = new PetRegister(1, getPetName(), getPetBirthday(), getPetBreed(), getPetBase64Image());
    }

    @Override
    public ArrayList<PetRegister> getPetData() {
        ArrayList<PetRegister> petRegisters = new ArrayList<>();
        petRegisters.add(0,petLoverModel);
        return petRegisters;
    }

    @Override
    public void successRequest(List<PetRedVetModel> data) {
        adapter.bindList(data);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_FILE && resultCode == Activity.RESULT_OK) {
            // selectGalleryButton();
            startCrop(data.getData(), isPetImage);
        } else if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            //selectSelfieButton();
            startCrop(Uri.fromFile(pictureFile), isPetImage);
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                displayPhoto(isPetImage, true);
            } else {
                croppedProfileFile = null;
                croppedPetFile = null;
                // unSelectButtons();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RegisterFragmentPresenter.PERMISSION_REQUEST_CAMERA_CODE) {
            if (grantResults.length <= 0 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (pictureFile == null) {
                    pictureFile = new File(getContext().getFilesDir(), PICTURE_FILE_NAME);
                }
                navigator.navigateToTakePictureCamera(this, pictureFile, REQUEST_CAMERA);
            }
        } else if (requestCode == RegisterFragmentPresenter.PERMISSION_REQUEST_GALLERY_CODE && (grantResults.length <= 0 || grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            navigator.navigateToGallery(this, SELECT_FILE);
        }
    }

    private void startCrop(Uri source, boolean isPetImage) {
        if (isPetImage) {
            croppedPetFile = new File(getContext().getFilesDir(), PICTURE_CROPPED_FILE_NAME);
            CropImage.activity(source)
                    .setCropShape(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? CropImageView.CropShape.RECTANGLE : CropImageView.CropShape.OVAL)                    .setFixAspectRatio(true)
                    .setBorderCornerThickness(0)
                    .setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                    .setOutputCompressQuality(50)
                    .setMaxCropResultSize(1800, 1800)
                    .setOutputUri(Uri.fromFile(croppedPetFile))
                    .start(getContext(), this);
        } else {
            croppedProfileFile = new File(getContext().getFilesDir(), PICTURE_CROPPED_FILE_NAME);
            CropImage.activity(source)
                    .setCropShape(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? CropImageView.CropShape.RECTANGLE : CropImageView.CropShape.OVAL)                    .setFixAspectRatio(true)
                    .setBorderCornerThickness(0)
                    .setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                    .setOutputCompressQuality(50)
                    .setMaxCropResultSize(1800, 1800)
                    .setOutputUri(Uri.fromFile(croppedProfileFile))
                    .start(getContext(), this);
        }
    }

    public void displayPhoto(boolean isPetImage, boolean refresh) {
        GlideApp.with(activity)
                .asBitmap()
                .dontAnimate()
                .diskCacheStrategy(refresh ? DiskCacheStrategy.NONE : DiskCacheStrategy.ALL)
                .skipMemoryCache(refresh)
                .placeholder(R.drawable.ic_placeholder)
                .load(isPetImage ? croppedPetFile.getAbsolutePath() : croppedProfileFile.getAbsolutePath())
                .into(isPetImage ? imgPet : imgRegister);
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

    @Override
    public String getProfileBase64Image() {
        if (croppedProfileFile != null) {
            Bitmap bm = BitmapFactory.decodeFile(croppedProfileFile.getAbsolutePath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b, Base64.NO_WRAP);
        } else
            return null;
    }

    @Override
    public String getPetBase64Image() {
        if (croppedPetFile != null) {
            Bitmap bm = BitmapFactory.decodeFile(croppedPetFile.getAbsolutePath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b, Base64.NO_WRAP);
        } else
            return null;
    }

    @OnClick(R.id.pet_birthday)
    public void petBirthday(){
        navigator.navigateToDatePicker(this);
    }

    @Override
    public String getDeviceId() {
        return FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        etPetBirthday.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void data(List<PetRedVetModel> data) {

    }
}
