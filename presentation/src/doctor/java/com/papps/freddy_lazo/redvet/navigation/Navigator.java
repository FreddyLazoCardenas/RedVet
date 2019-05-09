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
import com.papps.freddy_lazo.redvet.model.NewsModel;
import com.papps.freddy_lazo.redvet.model.ServiceDoctorModel;
import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;
import com.papps.freddy_lazo.redvet.view.activity.CancelAppointmentActivity;
import com.papps.freddy_lazo.redvet.view.activity.ChatActivity;
import com.papps.freddy_lazo.redvet.view.activity.DiagnoseAppointmentActivity;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.activity.LoginActivity;
import com.papps.freddy_lazo.redvet.view.activity.MapActivity;
import com.papps.freddy_lazo.redvet.view.activity.NewsDetailActivity;
import com.papps.freddy_lazo.redvet.view.activity.RegisterActivity;
import com.papps.freddy_lazo.redvet.view.activity.ServicesActivity;
import com.papps.freddy_lazo.redvet.view.dialogFragment.BaseDialogFragment;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CameraDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CancelOtherReasonAppointmentDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.ConfirmedAppointmentDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.FinishAppointmentDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.FinishedAppointmentDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.NotificationsListDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.PendingAppointmentDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.PhotoListDialog;
import com.papps.freddy_lazo.redvet.view.fragment.AppointmentFragment;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;
import com.papps.freddy_lazo.redvet.view.fragment.NewsFragment;
import com.papps.freddy_lazo.redvet.view.fragment.NotificationsFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ProfileFragment;
import com.papps.freddy_lazo.redvet.view.fragment.RegisterFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ForgotPasswordFragment;
import com.papps.freddy_lazo.redvet.view.fragment.LoginFragment;
import com.papps.freddy_lazo.redvet.view.fragment.MainMenuFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ServicesFragment;
import com.papps.freddy_lazo.redvet.view.pickers.TimePickerFragment;

import java.io.File;
import java.util.List;
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

    public void navigateToMapActivity(Fragment fragment, int requestCode) {
        fragment.startActivityForResult(MapActivity.getCallingIntent(fragment), requestCode);
    }

    public void navigateCancelAppointmentActivity(BaseDialogFragment fragment, int id, int requestCode) {
        fragment.startActivityForResult(CancelAppointmentActivity.getCallingIntent(fragment, id), requestCode);
    }

    public void navigateToDiagnoseAppointmentActivity(BaseDialogFragment fragment, String data, int requestCode) {
        fragment.startActivityForResult(DiagnoseAppointmentActivity.getCallingIntent(fragment, data), requestCode);
    }

    public void navigateToServicesActivity(BaseFragment fragment, List<ServiceDoctorModel> data, int requestCode) {
        fragment.startActivityForResult(ServicesActivity.getCallingIntent(fragment, data), requestCode);
    }

    public void navigateToNewsDetailActivity(BaseActivity activity, NewsModel data) {
        activity.startActivity(NewsDetailActivity.getCallingIntent(activity, data));
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
                fragmentTransaction(activity, AppointmentFragment.newInstance(), false);
                break;
            case R.id.action_profile:
                fragmentTransaction(activity, ProfileFragment.newInstance(), false);
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

    public void navigateToTakePictureCamera(BaseActivity activity, File photoFile, int requestCode) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(Objects.requireNonNull(activity.getBaseContext()).getPackageManager()) != null) {
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(activity,
                        BuildConfig.APPLICATION_ID + ".fileprovider",
                        photoFile);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    takePictureIntent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    activity.startActivityForResult(takePictureIntent, requestCode);
                } else {
                    takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    activity.startActivityForResult(takePictureIntent, requestCode);
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

    public void navigateToGallery(BaseActivity activity, int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select File"), requestCode);
    }
    //Dialogs

    public void showFinishListDialog(BaseActivity activity, FinishAppointmentDialog.OnClickListener listener, int id) {
        dialogTransaction(activity, FinishAppointmentDialog.newInstance(listener,id));
    }
    public void showListDialog(BaseActivity activity, CameraDialog.OnClickListener listener) {
        dialogTransaction(activity, CameraDialog.newInstance(listener));
    }

    public void showPhotoListDialog(BaseActivity activity, PhotoListDialog.OnClickListener listener) {
        dialogTransaction(activity, PhotoListDialog.newInstance(listener));
    }

    public void showNotificationsListDialog(BaseActivity activity, NotificationsListDialog.OnClickListener listener) {
        dialogTransaction(activity, NotificationsListDialog.newInstance(listener));
    }
    public void navigatePendingDialog(BaseActivity activity, String data, PendingAppointmentDialog.RequestInterface listener) {
        dialogTransaction(activity, PendingAppointmentDialog.newInstance(data, listener));
    }

    public void navigateConfirmedDialog(BaseActivity activity, String data, ConfirmedAppointmentDialog.RequestInterface listener) {
        dialogTransaction(activity, ConfirmedAppointmentDialog.newInstance(data, listener));
    }

    public void navigateFinishedDialog(BaseActivity activity, String data, FinishedAppointmentDialog.RequestInterface listener) {
        dialogTransaction(activity, FinishedAppointmentDialog.newInstance(data, listener));
    }

    public void navigateOtherReasonCancelAppointment(BaseActivity activity, int id, CancelOtherReasonAppointmentDialog.SuccessRequest listener) {
        dialogTransaction(activity, CancelOtherReasonAppointmentDialog.newInstance(id, listener));
    }

    public void navigateToChatActivity(BaseActivity activity, int appointmentId) {
        activity.startActivity(ChatActivity.getCallingIntent(activity, appointmentId));
    }

    public void navigatePhoneCall(BaseActivity activity, String phone) {
        activity.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null)));
    }

    public void navigateToTimePicker(BaseActivity activity) {
        TimePickerFragment.newInstance(activity).show(activity.getSupportFragmentManager(), "timePicker");
    }

    public void navigateToTimePicker(BaseFragment fragment) {
        TimePickerFragment.newInstance(fragment).show(Objects.requireNonNull(fragment.getFragmentManager()), "datePicker");
    }
}
