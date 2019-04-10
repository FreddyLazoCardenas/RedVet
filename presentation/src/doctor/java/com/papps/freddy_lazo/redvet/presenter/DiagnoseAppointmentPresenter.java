package com.papps.freddy_lazo.redvet.presenter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorAppointmentFinish;
import com.papps.freddy_lazo.domain.model.RedVetAppointment;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.DiagnoseAppointmentView;

import javax.inject.Inject;


public class DiagnoseAppointmentPresenter implements Presenter<DiagnoseAppointmentView> {


    public static final int PERMISSION_REQUEST_CAMERA_CODE = 4;
    public static final int PERMISSION_REQUEST_GALLERY_CODE = 5;
    private final DoctorAppointmentFinish doctorAppointmentFinish;
    private DiagnoseAppointmentView view;

    @Inject
    public DiagnoseAppointmentPresenter(DoctorAppointmentFinish doctorAppointmentFinish) {
        this.doctorAppointmentFinish = doctorAppointmentFinish;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void validate() {
        if (!validateDiagnoseField(view.getDiagnose()))
            return;
        sendRequest();
    }

    private boolean validateDiagnoseField(String diagnose){
        if(TextUtils.isEmpty(diagnose)){
            view.showErrorMessage(view.context().getString(R.string.add_diagnose_data));
            return false;
        }
        return true;
    }

    private void sendRequest() {
        doctorAppointmentFinish.bindParams(view.getApiToken(), view.getAppointmentId(), view.getDiagnose());
        doctorAppointmentFinish.execute(new AppointmentFinishObservable());
    }

    public boolean checkCameraHardware() {
        return view.context().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(view.context(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(PERMISSION_REQUEST_CAMERA_CODE);
            return false;
        }
        return true;
    }

    private void requestPermissions(int permissionCode) {
        if (permissionCode == PERMISSION_REQUEST_CAMERA_CODE) {
            view.requestCameraPermission();
        } else if (permissionCode == PERMISSION_REQUEST_GALLERY_CODE) {
            view.requestGalleryPermission();
        }
    }

    public boolean checkGalleryPermissions() {
        if (ContextCompat.checkSelfPermission(view.context(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(PERMISSION_REQUEST_GALLERY_CODE);
            return false;
        }
        return true;
    }

    @Override
    public void destroy() {
        doctorAppointmentFinish.unsubscribe();
    }

    @Override
    public void setView(DiagnoseAppointmentView view) {
        this.view = view;
    }

    private class AppointmentFinishObservable extends DefaultObserver<RedVetAppointment> {
        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(RedVetAppointment redVetAppointment) {
            super.onNext(redVetAppointment);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.showErrorMessage("exitooo");
            view.successRequest();
        }
    }
}
