package com.papps.freddy_lazo.redvet.presenter;

import android.text.TextUtils;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorAppointmentFinish;
import com.papps.freddy_lazo.domain.model.RedVetAppointment;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.DiagnoseAppointmentView;

import javax.inject.Inject;


public class DiagnoseAppointmentPresenter implements Presenter<DiagnoseAppointmentView> {

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
