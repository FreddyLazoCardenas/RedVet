package com.papps.freddy_lazo.redvet.navigation;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;

import com.papps.freddy_lazo.redvet.BuildConfig;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.view.activity.AppointmentActivity;
import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.activity.LoginActivity;
import com.papps.freddy_lazo.redvet.view.activity.RegisterActivity;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CameraDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.DoctorDialog;
import com.papps.freddy_lazo.redvet.view.fragment.MapFragment;
import com.papps.freddy_lazo.redvet.view.fragment.NewsFragment;
import com.papps.freddy_lazo.redvet.view.fragment.NotificationsFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ProfileFragment;
import com.papps.freddy_lazo.redvet.view.fragment.QuotesFragment;
import com.papps.freddy_lazo.redvet.view.fragment.RegisterFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ForgotPasswordFragment;
import com.papps.freddy_lazo.redvet.view.fragment.LoginFragment;
import com.papps.freddy_lazo.redvet.view.fragment.MainMenuFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ServicesFragment;
import com.papps.freddy_lazo.redvet.view.pickers.DatePickerFragment;
import com.papps.freddy_lazo.redvet.view.pickers.TimePickerFragment;

import java.io.File;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Navigator extends BaseNavigator {

    @Inject
    public Navigator() {
        //empty
    }

    //activities

    public void navigateToLoginActivity(BaseActivity activity) {
        activity.startActivity(LoginActivity.getCallingIntent(activity));
    }

    public void navigateToRegisterActivity(BaseActivity activity) {
        activity.startActivity(RegisterActivity.getCallingIntent(activity));
    }

    public void navigateToHomeActivity(BaseActivity activity) {
        activity.startActivity(HomeActivity.getCallingIntent(activity));
    }

    public void navigateToAppointmentActivity(BaseActivity activity) {
        activity.startActivity(AppointmentActivity.getCallingIntent(activity));
    }


    //fragments

    public void navigateToLoginFragment(BaseActivity activity) {
        fragmentTransaction(activity, LoginFragment.newInstance(), false);
    }

    public void navigateToRegisterFragment(BaseActivity activity) {
        fragmentTransaction(activity, RegisterFragment.newInstance(), false);
    }

    public void navigateToForgotPasswordFragment(BaseActivity activity) {
        fragmentTransaction(activity, ForgotPasswordFragment.newInstance(), true);
    }

    public void navigateToMainMenuFragment(BaseActivity activity) {
        fragmentTransaction(activity, MainMenuFragment.newInstance(), true);
    }

    public void navigateToServicesFragment(BaseActivity activity) {
        fragmentTransaction(activity, ServicesFragment.newInstance(), true);
    }

    public void navigateFromMenu(int itemId, BaseActivity activity) {
        activity.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        switch (itemId) {
            case R.id.action_quotes:
                fragmentTransaction(activity, QuotesFragment.newInstance(), false);
                break;
            case R.id.action_profile:
                fragmentTransaction(activity, ProfileFragment.newInstance(), false);
                break;
            case R.id.action_map:
                fragmentTransaction(activity, MapFragment.newInstance(), false);
                break;
            case R.id.action_news:
                fragmentTransaction(activity, NewsFragment.newInstance(), false);
                break;
            case R.id.action_notifications:
                fragmentTransaction(activity, NotificationsFragment.newInstance(), false);
                break;
            default:
                break;
        }
    }

    public void navigateToTakePictureCamera(Fragment fragment, File photoFile, int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(Objects.requireNonNull(fragment.getContext()).getPackageManager()) != null) {
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(fragment.getContext(),
                        BuildConfig.APPLICATION_ID + ".fileprovider",
                        photoFile);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    takePictureIntent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    fragment.startActivityForResult(takePictureIntent, requestCode);
                } else {
                    takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    fragment.startActivityForResult(takePictureIntent, requestCode);
                }


            } else {
                throw new NullPointerException("File for saving picture is null.");
            }
        }
    }

    public void navigateToGallery(Fragment fragment, int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        fragment.startActivityForResult(Intent.createChooser(intent, "Select File"), requestCode);
    }

    public void navigateDoctorDetailFragment(FragmentManager fm, String name , String doctor) {
        DoctorDialog.newInstance(doctor).show(fm, name);
    }
    //Dialogs

    public void showListDialog(BaseActivity activity, CameraDialog.OnClickListener listener) {
        dialogTransaction(activity, CameraDialog.newInstance(listener));
    }

    // Pickers

    public void navigateToDatePicker(BaseActivity activity){
        DatePickerFragment.newInstance(activity).show(activity.getSupportFragmentManager(), "datePicker");
    }

    public void navigateToTimePicker(BaseActivity activity){
        TimePickerFragment.newInstance(activity).show(activity.getSupportFragmentManager(), "timePicker");
    }

}
