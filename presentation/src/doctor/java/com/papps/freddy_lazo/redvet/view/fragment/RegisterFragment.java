package com.papps.freddy_lazo.redvet.view.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.RegisterFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerRegisterFragmentComponent;
import com.papps.freddy_lazo.redvet.model.ServicesModel;
import com.papps.freddy_lazo.redvet.presenter.RegisterFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.RegisterActivity;
import com.papps.freddy_lazo.redvet.view.adapter.PetAdapter;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CameraDialog;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment implements RegisterFragmentView, CameraDialog.OnClickListener {

    private static final String PICTURE_FILE_NAME = "profileComplete.jpg";
    private static final String PICTURE_CROPPED_FILE_NAME = "profile.jpg";

    private static final int SELECT_FILE = 1;
    private static final int REQUEST_CAMERA = 0;

    private RegisterActivity activity;
    private File pictureFile;
    private File croppedFile;

    @Inject
    RegisterFragmentPresenter presenter;
    @Inject
    PetAdapter adapter;

    @BindView(R.id.rv_pet)
    RecyclerView recyclerView;
    @BindView(R.id.img_register)
    ImageView imgRegister;

    @BindView(R.id.til_number)
    TextInputLayout tilNumber;
    @BindView(R.id.til_rs)
    TextInputLayout tilRs;
    @BindView(R.id.til_name)
    TextInputLayout tilName;
    @BindView(R.id.til_last_name)
    TextInputLayout tilLastName;

    @BindView(R.id.edt_number)
    EditText etNumber;
    @BindView(R.id.edt_rs)
    EditText etRs;
    @BindView(R.id.edt_name)
    EditText etName;
    @BindView(R.id.edt_last_name)
    EditText etLastName;
    @BindView(R.id.edt_address)
    EditText etAddress;
    @BindView(R.id.edt_email)
    EditText etEmail;
    @BindView(R.id.edt_phone)
    EditText etPhone;
    @BindView(R.id.edt_password)
    EditText etPassword;
    @BindView(R.id.edt_job)
    EditText etJob;
    @BindView(R.id.edt_tuition)
    EditText etTuition;
    @BindView(R.id.attention_spinner)
    Spinner spinner;
    @BindView(R.id.group_shower)
    android.support.constraint.Group gShower;
    @BindView(R.id.group_consultation)
    android.support.constraint.Group gConsultation;

    private boolean fromServices;


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
        initUI();
    }

    @Override
    public void initUI() {
        presenter.setView(this);
        setUpPetRv();
        setUpSpinner();
    }

    private void setUpSpinner() {
        ArrayList<String> arrayData = new ArrayList<>();
        arrayData.add("Casa");
        arrayData.add("Local");
        arrayData.add("Ambos");

        String[] arrayListData = arrayData.toArray(new String[0]);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                activity, R.layout.spinner_item, arrayListData);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    private void setUpPetRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter.bindList(false);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    @OnClick(R.id.btn_register)
    public void btnRegister() {
        // navigator.navigateToHomeActivity(activity);
        presenter.validateData();
    }

    @OnClick(R.id.img_register)
    public void imgRegister() {
        navigator.showListDialog(activity, this);
    }

    @OnClick(R.id.img_add_services)
    public void services() {
        fromServices = true;
        gShower.setVisibility(View.INVISIBLE);
        gConsultation.setVisibility(View.INVISIBLE);
        navigator.navigateToServicesFragment(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (fromServices) {
            fromServices = false;
            List<ServicesModel> servicesModelList = activity.getData();
            if (!servicesModelList.isEmpty()) {
                for (ServicesModel servicesModel : servicesModelList) {
                    if (servicesModel.getState() && servicesModel.getName().toLowerCase().contains("consultas")) {
                        gConsultation.setVisibility(View.VISIBLE);
                    }
                    if (servicesModel.getState() && servicesModel.getName().toLowerCase().contains("baños")) {
                        gShower.setVisibility(View.VISIBLE);
                    }
                }
            }
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
    public String getNumber() {
        return etNumber.getText().toString();
    }

    @Override
    public String getBusinessName() {
        return etRs.getText().toString();
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
    public String getJob() {
        return etJob.getText().toString();
    }

    @Override
    public String getTuition() {
        return etTuition.getText().toString();
    }

    @Override
    public void showDocumentNumberError(String message) {
        showError(tilNumber, etNumber, message);
    }

    @Override
    public void showBusinessNameError(String message) {
        showError(tilRs, etRs, message);

    }

    @Override
    public void showNameError(String message) {
        showError(tilName, etName, message);
    }

    @Override
    public void showLastNameError(String message) {
        showError(tilLastName, etLastName, message);
    }

    @Override
    public void showAddressError(String message) {
        showEtError(etAddress, message);
    }

    @Override
    public void showEmailError(String message) {
        showEtError(etEmail, message);
    }

    @Override
    public void showPhoneError(String message) {
        showEtError(etPhone, message);
    }

    @Override
    public void showPasswordError(String message) {
        showEtError(etPassword, message);
    }

    @Override
    public void showJobError(String message) {
        showEtError(etJob, message);
    }

    @Override
    public void showTuitionError(String message) {
        showEtError(etTuition, message);
    }

    @Override
    public void hideDocumentNumberError() {
        hideError(tilNumber);
    }

    @Override
    public void hideBusinessNameError() {
        hideError(tilRs);
    }

    @Override
    public void hideNameError() {
        hideError(tilName);
    }

    @Override
    public void hideLastNameError() {
        hideError(tilLastName);
    }

    @Override
    public void hideAddressError() {
        hideEtError(etAddress);
    }

    @Override
    public void hideEmailError() {
        hideEtError(etEmail);
    }

    @Override
    public void hidePhoneError() {
        hideEtError(etPhone);
    }

    @Override
    public void hidePasswordError() {
        hideEtError(etPassword);
    }

    @Override
    public void hideJobError() {
        hideEtError(etJob);
    }

    @Override
    public void hideTuitionError() {
        hideEtError(etTuition);
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
                displayPhoto(croppedFile.getAbsolutePath(), true);
            } else {
                croppedFile = null;
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

    private void startCrop(Uri source) {
        croppedFile = new File(getContext().getFilesDir(), PICTURE_CROPPED_FILE_NAME);
        CropImage.activity(source)
                .setCropShape(CropImageView.CropShape.OVAL)
                .setFixAspectRatio(true)
                .setBorderCornerThickness(0)
                .setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                .setOutputCompressQuality(50)
                .setMaxCropResultSize(1800, 1800)
                .setOutputUri(Uri.fromFile(croppedFile))
                .start(getContext(), this);

    }

    public void displayPhoto(String photoUrl, boolean refresh) {
        GlideApp.with(activity)
                .asBitmap()
                .dontAnimate()
                .diskCacheStrategy(refresh ? DiskCacheStrategy.NONE : DiskCacheStrategy.ALL)
                .skipMemoryCache(refresh)
                .placeholder(R.drawable.ic_placeholder)
                .load(photoUrl)
                .into(imgRegister);
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
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
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
