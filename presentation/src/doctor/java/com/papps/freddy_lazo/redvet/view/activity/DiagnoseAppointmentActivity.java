package com.papps.freddy_lazo.redvet.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.papps.freddy_lazo.redvet.model.AppointmentPhotoModel;
import com.papps.freddy_lazo.redvet.model.DoctorAppointmentModel;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.presenter.DiagnoseAppointmentPresenter;
import com.papps.freddy_lazo.redvet.presenter.RegisterFragmentPresenter;
import com.papps.freddy_lazo.redvet.util.DateHelper;
import com.papps.freddy_lazo.redvet.view.adapter.AppointmentPhotoAdapter;
import com.papps.freddy_lazo.redvet.view.dialogFragment.BaseDialogFragment;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CameraDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.DiagnoseDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.PhotoListDialog;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.MessageFormat;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class DiagnoseAppointmentActivity extends BaseActivity implements DiagnoseAppointmentView, DiagnoseDialog.OnClickListener, AppointmentPhotoAdapter.onClickAdapter, PhotoListDialog.OnClickListener {

    private static final String PICTURE_FILE_NAME = "profileComplete.jpg";
    private static final String PICTURE_CROPPED_FILE_NAME = "profile.jpg";
    private static final int REQUEST_CAMERA = 0;
    private static final int SELECT_FILE = 1;
    private static final int SELECT_FILE_PDF = 2;
    private static final int SELECT_FILE_DOC = 3;
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
    @BindView(R.id.rv_attach)
    RecyclerView rvAttach;
    @Inject
    AppointmentPhotoAdapter adapter;


    private DoctorAppointmentModel model;
    private int photoId;

    public static Intent getCallingIntent(BaseDialogFragment fragment, String data) {
        return new Intent(fragment.getContext(), DiagnoseAppointmentActivity.class).putExtra("data", data);
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
        setUpRv();
        fillUi();
    }

    private void setUpRv() {
        rvAttach.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvAttach.setAdapter(adapter);
        adapter.setListener(this);
    }

    private void fillUi() {
        displayPhoto(model.getPet().getPhoto_url(), false);
        petName.setText(model.getPet().getName());
        Calendar calendar = DateHelper.convertToDate(model.getPet().getBirthday());
        petBirthday.setText(MessageFormat.format("{0} {1} {2}", calendar.get(Calendar.DAY_OF_MONTH), DateHelper.getMonthForInt(calendar.get(Calendar.MONTH)).substring(0, 3), calendar.get(Calendar.YEAR)).replaceAll(",",""));
        Calendar calendar1 = DateHelper.convertToDate(model.getDate());
        appointmentDate.setText(MessageFormat.format("{0} {1}", calendar1.get(Calendar.DAY_OF_MONTH), DateHelper.getMonthForInt(calendar1.get(Calendar.MONTH)).substring(0, 3)));
        appointmentTime.setText(model.getTime());
        adapter.bindList(model.getPhotos());
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
        setResult(RESULT_OK);
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
    public int getAppointmentPhotoId() {
        return photoId;
    }

    @Override
    public void successDelete() {
        adapter.itemDeleted(getAppointmentPhotoId());
    }

    @Override
    public void successPhotoUpload(AppointmentPhotoModel data) {
        adapter.itemAdded(data);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_FILE && resultCode == Activity.RESULT_OK) {
            startCrop(data.getData());
        } else if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            startCrop(Uri.fromFile(pictureFile));
        } else if (requestCode == SELECT_FILE_PDF && resultCode == Activity.RESULT_OK) {
           // uploadFile();
        } else if (requestCode == SELECT_FILE_DOC && resultCode == Activity.RESULT_OK) {
           // uploadFile();
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                displayPhoto(croppedFile.getAbsolutePath(), true);
                presenter.uploadPhoto(getPetDiagnoseBase64Image());
            } else {
                croppedFile = null;
            }
        }
    }

    public byte[] getPetDiagnoseBase64Image() {
        if (croppedFile != null) {
            Bitmap bm = BitmapFactory.decodeFile(croppedFile.getAbsolutePath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            //return Base64.encodeToString(b, Base64.NO_WRAP);
            return b;
        } else
            return null;
    }


    private void startCrop(Uri source) {
        croppedFile = new File(getFilesDir(), PICTURE_CROPPED_FILE_NAME);
        CropImage.activity(source)
                .setCropShape(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? CropImageView.CropShape.RECTANGLE : CropImageView.CropShape.OVAL).setFixAspectRatio(true)
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
    public void ivAttach() {
        if (adapter.getItemCount() < 5)
            navigator.showDiagnoseListDialog(this, this);
        else
            showErrorMessage("Máximo número de documentos alcanzado");
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

    @Override
    public void pdf() {
        navigator.navigateToPdf(this, SELECT_FILE_PDF);
    }

    @Override
    public void documents() {
        navigator.navigateToDocs(this, SELECT_FILE_DOC);
    }

    @OnClick(R.id.img_header)
    public void imgHeader() {
        finish();
    }

    @Override
    public void itemClicked(AppointmentPhotoModel data) {
        photoId = data.getId();
        navigator.showPhotoListDialog(this, this);
    }

    @Override
    public void delete() {
        presenter.deletePhoto();
    }

    @Override
    public void cancel() {

    }
}
