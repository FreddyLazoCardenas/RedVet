package com.papps.freddy_lazo.redvet.view.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.iid.FirebaseInstanceId;
import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.ProfileFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerProfileFragmentComponent;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.model.ScheduleModel;
import com.papps.freddy_lazo.redvet.model.ServiceDoctorModel;
import com.papps.freddy_lazo.redvet.model.ServicesModel;
import com.papps.freddy_lazo.redvet.presenter.ProfileFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CameraDialog;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment implements CameraDialog.OnClickListener, ProfileFragmentView {

    public static final int PERMISSION_REQUEST_CAMERA_CODE = 4;
    public static final int PERMISSION_REQUEST_GALLERY_CODE = 5;
    private static final int MAP_REQUEST_CODE = 2;


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
    @BindView(R.id.attention_spinner)
    Spinner spinner;
    @BindView(R.id.type_spinner)
    Spinner typeSpinner;
    @BindView(R.id.til_name)
    TextInputLayout tilName;
    @BindView(R.id.til_last_name)
    TextInputLayout tilLastName;
    @BindView(R.id.toggle)
    RadioGroup toggle;
    @BindView(R.id.edt_rs)
    EditText etRs;
    @BindView(R.id.et_consultation_price)
    EditText etConsultationPrice;
    @BindView(R.id.et_shower_price)
    EditText etShowerPrice;
    @BindView(R.id.et_tuition)
    EditText etTuition;
    @BindView(R.id.et_description)
    TextInputEditText etDescription;
    @BindView(R.id.group_shower)
    Group gShower;
    @BindView(R.id.group_consultation)
    Group gConsultation;

    private File pictureFile;
    private HomeActivity activity;
    private DoctorModel doctorModel;
    private File croppedProfileFile;
    private Double latitude;
    private Double longitude;
    private boolean fromServices;
    private List<ServicesDoctorRegister> servicesDoctorRegisterList = new ArrayList<>();


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
        setUpSpinner();
        setUpTypeSpinner();
    }

    private void getUserData() {
        doctorModel = DoctorModel.toModel(preferencesManager.getDoctorCurrentUser());
        fillUi();
    }

    private void setUpTypeSpinner() {
        ArrayList<String> arrayData = new ArrayList<>();
        arrayData.add("Clinica");
        arrayData.add("Veterinaria");
        arrayData.add("Otro");

        String[] arrayListData = arrayData.toArray(new String[0]);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                activity, R.layout.spinner_item, arrayListData);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(spinnerArrayAdapter);
        typeSpinner.setSelection(setTypeSpinnerItem(doctorModel.getType()));
    }

    private int setTypeSpinnerItem(String type) {
        switch (type) {
            case "clinic":
                return 0;
            case "vet":
                return 1;
            case "other":
                return 2;
            default:
                return 0;
        }
    }

    private void getData(Intent data) {
        if (data != null) {
            String address = data.getStringExtra("address");
            latitude = data.getDoubleExtra("latitude", -1);
            longitude = data.getDoubleExtra("longitude", -1);
            etAddress.setText(address);
        }
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
        spinner.setSelection(setSpinnerItem(doctorModel.getAttention()));
    }

    private int setSpinnerItem(String attention) {
        switch (attention) {
            case "at_home":
                return 0;
            case "local":
                return 1;
            case "both":
                return 2;
            default:
                return 0;
        }
    }

    private void fillUi() {
        latitude = Double.valueOf(doctorModel.getLatitude());
        longitude = Double.valueOf(doctorModel.getLongitude());
        String typeDocument = doctorModel.getType_document();
        toggle.check(typeDocument.equals("dni") ? R.id.dni : R.id.ruc);
        etName.setText(doctorModel.getFirst_name());
        etLastName.setText(doctorModel.getLast_name());
        etNumber.setText(doctorModel.getNumber_document());
        etAddress.setText(doctorModel.getAddress());
        etEmail.setText(doctorModel.getEmail());
        etRs.setText(doctorModel.getBusiness_name());
        etPhone.setText(doctorModel.getPhone());
        displayPhoto(true, true);
        etDescription.setText(doctorModel.getDescription());
        showerLogic();
        consultationLogic();
        // setUpRv();
    }

    private void showerLogic() {
        if (doctorModel.getShower_price() != null && !doctorModel.getShower_price().equals("")) {
            gShower.setVisibility(View.VISIBLE);
            etShowerPrice.setText(doctorModel.getShower_price());
        } else {
            gShower.setVisibility(View.INVISIBLE);
        }
        gShower.requestLayout();
    }

    private void consultationLogic() {
        if (doctorModel.getConsultation_price() != null && !doctorModel.getConsultation_price().equals("")) {
            gConsultation.setVisibility(View.VISIBLE);
            etConsultationPrice.setText(doctorModel.getConsultation_price());
        } else {
            gConsultation.setVisibility(View.INVISIBLE);
        }
        gConsultation.requestLayout();
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

    @OnClick(R.id.img_add_services)
    public void services() {
        fromServices = true;
        gShower.setVisibility(View.INVISIBLE);
        gConsultation.setVisibility(View.INVISIBLE);
        gShower.requestLayout();
        gConsultation.requestLayout();
        navigator.navigateToServicesFragment(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (fromServices) {
            fromServices = false;
            servicesDoctorRegisterList.clear();
            List<ServicesModel> servicesModelList = activity.getData();
            if (!servicesModelList.isEmpty()) {
                for (ServicesModel servicesModel : servicesModelList) {
                    if (servicesModel.getState()) {
                        ServicesDoctorRegister data = new ServicesDoctorRegister(servicesModel.getId());
                        servicesDoctorRegisterList.add(data);
                    }
                    if (servicesModel.getState() && servicesModel.getName().toLowerCase().contains("consultas")) {
                        gConsultation.setVisibility(View.VISIBLE);
                    }
                    if (servicesModel.getState() && servicesModel.getName().toLowerCase().contains("baños")) {
                        gShower.setVisibility(View.VISIBLE);
                    }
                }
            } else {
                servicesDoctorRegisterList.clear();
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
        } else if (requestCode == MAP_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                getData(data);
            }
        }
    }

    private void startCrop(Uri source) {
        croppedProfileFile = new File(getContext().getFilesDir(), PICTURE_CROPPED_FILE_NAME);
        CropImage.activity(source)
                .setCropShape(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? CropImageView.CropShape.RECTANGLE : CropImageView.CropShape.OVAL)                .setFixAspectRatio(true)
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
        showMessage(activity, message);
    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    @Override
    public String getConsultationTime() {
        return getConsultationPriceVisibility() == View.VISIBLE ? "30" : null;
    }

    @Override
    public String getShowerPrice() {
        return etShowerPrice.getText().toString();
    }

    @Override
    public String getShowerTime() {
        return getShowerPriceVisibility() == View.VISIBLE ? "30" : null;
    }

    @Override
    public String getDescription() {
        return Objects.requireNonNull(etDescription.getText()).toString();
    }

    @Override
    public void updateView() {
        getUserData();
    }

    @Override
    public String getConsultationPrice() {
        return etConsultationPrice.getText().toString();
    }

    @Override
    public int getConsultationPriceVisibility() {
        return etConsultationPrice.getVisibility();
    }

    @Override
    public int getShowerPriceVisibility() {
        return etShowerPrice.getVisibility();
    }

    @Override
    public void showConsultationPriceError(String message) {
        showEtError(etConsultationPrice, message);
    }

    @Override
    public void hideConsultationPriceError() {
        hideEtError(etConsultationPrice);
    }

    @Override
    public void showShowerPriceError(String message) {
        showEtError(etShowerPrice, message);
    }

    @Override
    public void hideShowerPriceError() {
        hideEtError(etShowerPrice);
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
        return etName.getText().toString();
    }

    @Override
    public String getLastName() {
        return etLastName.getText().toString();
    }

    @Override
    public String getNumber() {
        return etNumber.getText().toString();
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
    public String getApiToken() {
        return doctorModel.getApi_token();
    }

    @Override
    public String getTypeDocument() {
        int id = toggle.getCheckedRadioButtonId();
        View radioButton = toggle.findViewById(id);
        int idx = toggle.indexOfChild(radioButton);
        return idx == 0 ? "dni" : "ruc";
    }

    @Override
    public String getLatitude() {
        return latitude != null ? latitude.toString() : "0";
    }

    @Override
    public String getLongitude() {
        return longitude != null ? longitude.toString() : "0";
    }

    @Override
    public String getBusinessName() {
        return etRs.getText().toString();
    }

    @Override
    public String getType() {
        switch (typeSpinner.getSelectedItemPosition()) {
            case 0:
                return "clinic";
            case 1:
                return "vet";
            case 2:
                return "other";
            default:
                return "other";
        }
    }

    @OnClick(R.id.et_address)
    public void edtAddress() {
        navigator.navigateToMapActivity(this, MAP_REQUEST_CODE);
    }

    @Override
    public String getAttention() {
        switch (spinner.getSelectedItemPosition()) {
            case 0:
                return "at_home";
            case 1:
                return "local";
            case 2:
                return "both";
            default:
                return "at_home";
        }
    }

    @Override
    public ArrayList<PetRegister> getPetData() {
        ArrayList<PetRegister> data = new ArrayList<>();
        for (PetLoverRegisterModel pet : doctorModel.getPetList()) {
            data.add(new PetRegister(pet.getId(), pet.getPet_id()));
        }
        return data;
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
    public void showDocumentNumber(String message) {
        showEtError(etNumber, message);
    }

    @Override
    public void hideDocumentNumber() {
        hideEtError(etNumber);
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
    public String getTuition() {
        return etTuition.getText().toString();
    }

    @Override
    public String getFcmToken() {
        return FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    public List<ScheduleDoctorRegister> getSchedules() {
        List<ScheduleDoctorRegister> list = new ArrayList<>();
        for (ScheduleModel scheduleModel : doctorModel.getScheduleList()) {
            list.add(new ScheduleDoctorRegister(scheduleModel.getId(), scheduleModel.getDay(), scheduleModel.getStart_time(), scheduleModel.getEnd_time()));
        }
        return list;
    }

    @Override
    public List<ServicesDoctorRegister> getServices() {
        List<ServicesDoctorRegister> list = new ArrayList<>();
        for (ServiceDoctorModel serviceDoctorModel : doctorModel.getServiceList()) {
            list.add(new ServicesDoctorRegister(serviceDoctorModel.getId(), serviceDoctorModel.getService_id()));
        }
        return list;
    }

    @Override
    public String getProfileBase64Image() {
        if (croppedProfileFile != null) {
            Bitmap bm = BitmapFactory.decodeFile(croppedProfileFile.getPath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            Log.d("profileImage",Base64.encodeToString(b, Base64.NO_WRAP));
            return Base64.encodeToString(b, Base64.NO_WRAP);
        } else
            return (doctorModel.getPhoto_url() != null && doctorModel.getPhoto_url().equals("")) ? null : doctorModel.getPhoto_url();
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
