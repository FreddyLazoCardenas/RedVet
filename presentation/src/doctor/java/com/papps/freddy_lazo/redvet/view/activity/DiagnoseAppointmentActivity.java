package com.papps.freddy_lazo.redvet.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.DiagnoseAppointmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerDiagnoseAppointmentComponent;
import com.papps.freddy_lazo.redvet.model.DoctorAppointmentModel;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.presenter.DiagnoseAppointmentPresenter;
import com.papps.freddy_lazo.redvet.presenter.RegisterFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CameraDialog;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class DiagnoseAppointmentActivity extends BaseActivity implements DiagnoseAppointmentView, CameraDialog.OnClickListener {

    private static final String PICTURE_FILE_NAME = "profileComplete.jpg";
    private static final String PICTURE_CROPPED_FILE_NAME = "profile.jpg";

    private static final int SELECT_FILE = 1;
    private static final int REQUEST_CAMERA = 0;
    private File pictureFile;
    private File croppedFile;

    @Inject
    DiagnoseAppointmentPresenter presenter;
    @Inject
    PreferencesManager preferencesManager;
    @BindView(R.id.et_diagnose)
    EditText etDiagnose;
    @BindView(R.id.img_pet)
    ImageView ivPet;
    @BindView(R.id.iv_diagnose)
    ImageView ivPetDiagnose;
    @BindView(R.id.tv_pet_name)
    TextView petName;
    @BindView(R.id.tv_pet_birthday)
    TextView petBirthday;
    @BindView(R.id.tv_date)
    TextView appointmentDate;
    @BindView(R.id.tv_time)
    TextView appointmentTime;


    private DoctorAppointmentModel model;

    public static Intent getCallingIntent(BaseActivity activity, String data) {
        return new Intent(activity, DiagnoseAppointmentActivity.class).putExtra("data", data);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose_appointment);
        injectView(this);
        buildInjection();
        initUI();
    }

    private void buildInjection() {
        DaggerDiagnoseAppointmentComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }


    @OnClick(R.id.btn_finish_appointment)
    public void btnFinishAppointment() {
        presenter.validate();
    }

    @Override
    public void initUI() {
        super.initUI();
        model = DoctorAppointmentModel.toModel(getIntent().getStringExtra("data"));
        presenter.setView(this);
        fillUi();
    }

    private void fillUi() {
        displayPhoto(model.getPetLover().getPhoto_url(), false);
        petName.setText(model.getPet().getName());
        petBirthday.setText(model.getPet().getBirthday());
        appointmentDate.setText(model.getDate());
        appointmentTime.setText(model.getTime());
    }

    public void displayPhoto(String photoUrl, boolean fromDiagnose) {
        GlideApp.with(this)
                .asBitmap()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ic_placeholder)
                .load(photoUrl)
                .into(fromDiagnose ? ivPetDiagnose : ivPet);
    }

    @Override
    public String getDiagnose() {
        return etDiagnose.getText().toString();
    }

    @Override
    public String getApiToken() {
        return DoctorModel.toModel(preferencesManager.getDoctorCurrentUser()).getApi_token();
    }

    @Override
    public int getAppointmentId() {
        return model.getId();
    }

    @Override
    public void successRequest() {
        finish();
    }

    @SuppressLint("NewApi")
    @Override
    public void requestCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, RegisterFragmentPresenter.PERMISSION_REQUEST_CAMERA_CODE);
    }

    @SuppressLint("NewApi")
    @Override
    public void requestGalleryPermission() {
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RegisterFragmentPresenter.PERMISSION_REQUEST_GALLERY_CODE);
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
                presenter.uploadPhoto(getPetDiagnoseBase64Image());
            } else {
                croppedFile = null;
                // unSelectButtons();
            }
        }
    }

    public String getPetDiagnoseBase64Image() {
        if (croppedFile != null) {
            Bitmap bm = BitmapFactory.decodeFile(croppedFile.getAbsolutePath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b, Base64.NO_WRAP);
        } else
            return null;
    }


    private void startCrop(Uri source) {
        croppedFile = new File(getFilesDir(), PICTURE_CROPPED_FILE_NAME);
        CropImage.activity(source)
                .setCropShape(CropImageView.CropShape.OVAL)
                .setFixAspectRatio(true)
                .setBorderCornerThickness(0)
                .setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                .setOutputCompressQuality(50)
                .setMaxCropResultSize(1800, 1800)
                .setOutputUri(Uri.fromFile(croppedFile))
                .start(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RegisterFragmentPresenter.PERMISSION_REQUEST_CAMERA_CODE) {
            if (grantResults.length <= 0 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (pictureFile == null) {
                    pictureFile = new File(getFilesDir(), PICTURE_FILE_NAME);
                }
                navigator.navigateToTakePictureCamera(this, pictureFile, REQUEST_CAMERA);
            }
        } else if (requestCode == RegisterFragmentPresenter.PERMISSION_REQUEST_GALLERY_CODE && (grantResults.length <= 0 || grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            navigator.navigateToGallery(this, SELECT_FILE);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @OnClick(R.id.iv_attach)
    public void ivAttach(){
        navigator.showListDialog(this,this);
    }

    @Override
    public void camera() {
        if (presenter.checkCameraHardware() && presenter.checkPermissions()) {
            if (pictureFile == null) {
                pictureFile = new File(getFilesDir(), PICTURE_FILE_NAME);
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
}
