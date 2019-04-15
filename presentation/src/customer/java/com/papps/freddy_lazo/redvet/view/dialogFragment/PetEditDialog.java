package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.PetEditDialogView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerPendingAppointmentDialogComponent;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerPetEditDialogComponent;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.presenter.PetEditDialogPresenter;
import com.papps.freddy_lazo.redvet.presenter.RegisterFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class PetEditDialog extends BaseDialogFragment implements CameraDialog.OnClickListener, PetEditDialogView {

    @BindView(R.id.pet_name)
    TextView petName;
    @BindView(R.id.pet_birthday)
    TextView petBirthday;
    @BindView(R.id.pet_breed)
    TextView petBreed;
    @BindView(R.id.img_add)
    ImageView ivPet;
    @Inject
    PetEditDialogPresenter presenter;


    private static final int SELECT_FILE = 1;
    private static final int REQUEST_CAMERA = 0;
    private static final String PICTURE_FILE_NAME = "profileComplete.jpg";
    private static final String PICTURE_CROPPED_PET_FILE_NAME = "pet.jpg";
    private PetEditInterface listener;
    private PetLoverRegisterModel model;
    private HomeActivity activity;
    private File pictureFile;
    private File croppedProfileFile;


    public static PetEditDialog newInstance(PetLoverRegisterModel model, PetEditInterface listener) {
        PetEditDialog dialog = new PetEditDialog();
        dialog.model = model;
        return dialog.setListener(listener);
    }

    private PetEditDialog setListener(PetEditInterface listener) {
        this.listener = listener;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_pet_edit, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (HomeActivity) getActivity();
        initUI();
    }


    private void fillUi() {
        petName.setText(model.getName());
        petBirthday.setText(model.getBirthday());
        petBreed.setText(model.getBreed());
        displayPhoto(model.getPhoto_url());
    }

    public void displayPhoto(String url) {
        GlideApp.with(activity)
                .asBitmap()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ic_placeholder)
                .load(url)
                .into(ivPet);
    }

    public String getPetBase64Image() {
        if (croppedProfileFile != null) {
            Bitmap bm = BitmapFactory.decodeFile(croppedProfileFile.getAbsolutePath());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b, Base64.NO_WRAP);
        } else
            return (model.getPhoto() != null ? getBase64FromImageView() : null);
    }

    private String getBase64FromImageView() {
        Bitmap bitmap = ((BitmapDrawable) ivPet.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.NO_WRAP);
    }

    @OnClick(R.id.btn_pet_save)
    public void savePet() {
        validateData();
        listener.updatePet(model);
        dismiss();
    }

    private void validateData() {
        if (!validatePetName(getPetName()))
            return;
        if (!validatePetBirthday(getPetName()))
            return;
        if (!validatePetBreed(getPetName()))
            return;
        updateMode();
    }

    private void updateMode() {
        model.setName(getPetName());
        model.setBirthday(getPetBirthday());
        model.setBreed(getPetBreed());
        model.setPhoto(getPetBase64Image());
        model.setPhoto_url(croppedProfileFile != null ? croppedProfileFile.getAbsolutePath() : null);
    }

    private boolean validatePetBreed(String petName) {
        if (TextUtils.isEmpty(petName)) {
            showMessage(activity, activity.getString(R.string.add_pet_breed));
            return false;
        }
        return true;
    }

    private boolean validatePetBirthday(String petName) {
        if (TextUtils.isEmpty(petName)) {
            showMessage(activity, activity.getString(R.string.add_pet_birthday));
            return false;
        }
        return true;
    }

    private boolean validatePetName(String petName) {
        if (TextUtils.isEmpty(petName)) {
            showMessage(activity, activity.getString(R.string.add_pet_mame));
            return false;
        }
        return true;
    }

    private String getPetName() {
        return petName.getText().toString();
    }

    private String getPetBirthday() {
        return petBirthday.getText().toString();
    }

    private String getPetBreed() {
        return petBreed.getText().toString();
    }

    @OnClick(R.id.img_add)
    public void addImage() {
        activity.getNavigator().showListDialog(activity, this);
    }

    private void buildInjection() {
        DaggerPetEditDialogComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_FILE && resultCode == Activity.RESULT_OK) {
            // selectGalleryButton();
            startCrop(data.getData());
        } else if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            //selectSelfieButton();
            startCrop(Uri.fromFile(pictureFile));
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                displayPhoto(croppedProfileFile.getAbsolutePath());
            } else {
                croppedProfileFile = null;
                // unSelectButtons();
            }
        }
    }

    private void startCrop(Uri source) {
        croppedProfileFile = new File(getContext().getFilesDir(), PICTURE_CROPPED_PET_FILE_NAME);
        CropImage.activity(source)
                .setCropShape(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? CropImageView.CropShape.RECTANGLE : CropImageView.CropShape.OVAL).setFixAspectRatio(true)
                .setBorderCornerThickness(0)
                .setOutputCompressFormat(Bitmap.CompressFormat.JPEG)
                .setOutputCompressQuality(50)
                .setMaxCropResultSize(1800, 1800)
                .setOutputUri(Uri.fromFile(croppedProfileFile))
                .start(getContext(), this);
    }

    @Override
    public void initUI() {
        presenter.setView(this);
        fillUi();
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

    public interface PetEditInterface {
        void updatePet(PetLoverRegisterModel model);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
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

    @Override
    public void requestCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, PetEditDialogPresenter.PERMISSION_REQUEST_CAMERA_CODE);
    }

    @Override
    public void requestGalleryPermission() {
        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PetEditDialogPresenter.PERMISSION_REQUEST_GALLERY_CODE);
    }
}
