package com.papps.freddy_lazo.redvet.navigation;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.papps.freddy_lazo.redvet.BuildConfig;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.NewsModel;
import com.papps.freddy_lazo.redvet.model.PetLoverRegisterModel;
import com.papps.freddy_lazo.redvet.model.ServiceDoctorModel;
import com.papps.freddy_lazo.redvet.view.activity.AppointmentActivity;
import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;
import com.papps.freddy_lazo.redvet.view.activity.CancelAppointmentActivity;
import com.papps.freddy_lazo.redvet.view.activity.ChatActivity;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.activity.LoginActivity;
import com.papps.freddy_lazo.redvet.view.activity.NewsDetailActivity;
import com.papps.freddy_lazo.redvet.view.activity.PhotoDetailActivity;
import com.papps.freddy_lazo.redvet.view.activity.RegisterActivity;
import com.papps.freddy_lazo.redvet.view.activity.ServicesActivity;
import com.papps.freddy_lazo.redvet.view.dialogFragment.BaseDialogFragment;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CameraDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.CancelOtherReasonAppointmentDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.ConfirmedAppointmentDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.DocListDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.DoctorDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.DoctorNotificationConfirmedDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.DoctorNotificationFinishedDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.FinishedAppointmentDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.NotificationsListDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.PendingAppointmentDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.PetEditDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.PetListDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.PetLoverListDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.PhotoListDialog;
import com.papps.freddy_lazo.redvet.view.dialogFragment.SuccessAppointmentDialog;
import com.papps.freddy_lazo.redvet.view.fragment.AppointmentFragment;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;
import com.papps.freddy_lazo.redvet.view.fragment.MapFragment;
import com.papps.freddy_lazo.redvet.view.fragment.NewsFragment;
import com.papps.freddy_lazo.redvet.view.fragment.NotificationsFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ProfileFragment;
import com.papps.freddy_lazo.redvet.view.fragment.RegisterFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ForgotPasswordFragment;
import com.papps.freddy_lazo.redvet.view.fragment.LoginFragment;
import com.papps.freddy_lazo.redvet.view.fragment.MainMenuFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ServicesFragment;
import com.papps.freddy_lazo.redvet.view.pickers.DatePickerFragment;
import com.papps.freddy_lazo.redvet.view.pickers.RangeTimePickerDialog;
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

    public void navigateToAppointmentActivity(BaseActivity activity, String doctorModel) {
        activity.startActivity(AppointmentActivity.getCallingIntent(activity, doctorModel));
    }

    public void navigateToChatActivity(BaseActivity activity, int appointmentId) {
        activity.startActivity(ChatActivity.getCallingIntent(activity, appointmentId));
    }

    public void navigateToNewsDetailActivity(BaseActivity activity, NewsModel data) {
        activity.startActivity(NewsDetailActivity.getCallingIntent(activity, data));
    }

    public void navigateToServicesActivity(BaseFragment fragment, List<ServiceDoctorModel> data, int requestCode) {
        fragment.startActivityForResult(ServicesActivity.getCallingIntent(fragment, data), requestCode);
    }

    public void navigateToServicesActivity(BaseActivity activity, List<ServiceDoctorModel> data, int requestCode) {
        activity.startActivityForResult(ServicesActivity.getCallingIntent(activity, data), requestCode);
    }

    public void navigateToPhotoDetailActivity(BaseActivity activity, String url) {
        activity.startActivity(PhotoDetailActivity.getCallingIntent(activity, url));
    }


    public void navigateToShowFiles(BaseActivity activity ,String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(browserIntent);
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


    //Dialogs

    public void showListDialog(BaseActivity activity, CameraDialog.OnClickListener listener) {
        dialogTransaction(activity, CameraDialog.newInstance(listener));
    }

    public void showPetListDialog(BaseActivity activity, PetListDialog.OnClickListener listener) {
        dialogTransaction(activity, PetListDialog.newInstance(listener));
    }

    public void showNotificationsListDialog(BaseActivity activity, NotificationsListDialog.OnClickListener listener) {
        dialogTransaction(activity, NotificationsListDialog.newInstance(listener));
    }

    public void navigateDoctorDetailFragment(BaseActivity activity, String doctor) {
        dialogTransaction(activity, DoctorDialog.newInstance(doctor));
    }

    public void navigateDoctorConfirmedAppointmentFragment(BaseActivity activity, String notification) {
        dialogTransaction(activity, DoctorNotificationConfirmedDialog.newInstance(notification));
    }

    public void navigateDoctorFinishedAppointmentFragment(BaseActivity activity, String notification) {
        dialogTransaction(activity, DoctorNotificationFinishedDialog.newInstance(notification));
    }

    public void navigateSuccessAppointment(BaseActivity activity) {
        dialogTransaction(activity, SuccessAppointmentDialog.newInstance());
    }

    // Pickers

    public void navigateToDatePicker(BaseActivity activity) {
        DatePickerFragment.newInstance(activity).show(activity.getSupportFragmentManager(), "datePicker");
    }

    public void navigateToDatePicker(BaseFragment fragment) {
        DatePickerFragment.newInstance(fragment).show(Objects.requireNonNull(fragment.getFragmentManager()), "datePicker");
    }

    public void navigateToDatePicker(BaseDialogFragment dialogFragment) {
        DatePickerFragment.newInstance(dialogFragment).show(Objects.requireNonNull(dialogFragment.getFragmentManager()), "datePicker");
    }

    public void navigateToTimePicker(BaseActivity activity) {
        TimePickerFragment.newInstance(activity).show(activity.getSupportFragmentManager(), "timePicker");
    }

    public RangeTimePickerDialog navigateToAppointmentTimePicker(BaseActivity activity) {
        return RangeTimePickerDialog.newInstance(activity);
    }

    public void navigatePendingDialog(BaseActivity activity, String data) {
        dialogTransaction(activity, PendingAppointmentDialog.newInstance(data));
    }

    public void navigateConfirmedDialog(BaseActivity activity, String data, ConfirmedAppointmentDialog.RequestInterface listener) {
        dialogTransaction(activity, ConfirmedAppointmentDialog.newInstance(data, listener));
    }

    public void navigateFinishedDialog(BaseActivity activity, String data) {
        dialogTransaction(activity, FinishedAppointmentDialog.newInstance(data));
    }

    public void navigateCancelAppointmentActivity(BaseDialogFragment fragment, int id, int requestCode) {
        fragment.startActivityForResult(CancelAppointmentActivity.getCallingIntent(fragment, id), requestCode);
    }

    public void navigateOtherReasonCancelAppointment(BaseActivity activity, int id, CancelOtherReasonAppointmentDialog.SuccessRequest listener) {
        dialogTransaction(activity, CancelOtherReasonAppointmentDialog.newInstance(id, listener));
    }

    public void showPhotoListDialog(BaseActivity activity, PhotoListDialog.OnClickListener listener) {
        dialogTransaction(activity, PhotoListDialog.newInstance(listener));
    }

    public void navigatePhoneCall(BaseActivity activity, String phone) {
        activity.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null)));
    }

    public void navigateWhatsApp(BaseActivity activity, String phone) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "51" + phone + "&text=" + "Hola "));
        if (sendIntent.resolveActivity(activity.getPackageManager()) == null) {
            Toast.makeText(activity, "Instalar whatsApp por favor", Toast.LENGTH_SHORT).show();
            return;
        }
        activity.startActivity(sendIntent);
    }

    public void navigateEditPet(BaseActivity activity, PetLoverRegisterModel model, PetEditDialog.PetEditInterface listener) {
        dialogTransaction(activity, PetEditDialog.newInstance(model, listener));
    }

    public void navigateToNavigation(BaseActivity activity, String latitude, String longitude) {

        String url = "waze://?ll=" + latitude + ", " + longitude + "&navigate=yes";
        Intent intentWaze = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intentWaze.setPackage("com.waze");

        String uriGoogle = "google.navigation:q=" + latitude + "," + longitude;
        Intent intentGoogleNav = new Intent(Intent.ACTION_VIEW, Uri.parse(uriGoogle));
        intentGoogleNav.setPackage("com.google.android.apps.maps");

        String title = "Elige un app para llegar a tu destino";
        Intent chooserIntent = Intent.createChooser(intentGoogleNav, title);
        Intent[] arr = new Intent[1];
        arr[0] = intentWaze;
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arr);
        activity.startActivity(chooserIntent);
    }

    public void showDocListDialog(BaseActivity activity, PetLoverListDialog.OnClickListener listener) {
        dialogTransaction(activity, PetLoverListDialog.newInstance(listener));
    }
}
