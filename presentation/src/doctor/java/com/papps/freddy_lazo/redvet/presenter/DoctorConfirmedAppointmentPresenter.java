package com.papps.freddy_lazo.redvet.presenter;

import android.text.TextUtils;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorAppointmentCancel;
import com.papps.freddy_lazo.domain.interactor.DoctorAppointmentConfirm;
import com.papps.freddy_lazo.domain.model.RedVetAppointment;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.ConfirmedAppointmentDialogView;
import com.papps.freddy_lazo.redvet.interfaces.PendingAppointmentDialogView;

import javax.inject.Inject;


public class DoctorConfirmedAppointmentPresenter implements Presenter<ConfirmedAppointmentDialogView> {

    private final DoctorAppointmentCancel doctorAppointmentCancel;
    private ConfirmedAppointmentDialogView view;

    @Inject
    public DoctorConfirmedAppointmentPresenter(DoctorAppointmentCancel doctorAppointmentCancel) {
        this.doctorAppointmentCancel = doctorAppointmentCancel;
    }

    public void sendRequest() {
        doctorAppointmentCancel.bindParams(view.getApiToken(), view.getAppointmentId(), view.getReason());
        doctorAppointmentCancel.execute(new DoctorConfirmObservable());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        doctorAppointmentCancel.unsubscribe();
    }

    @Override
    public void setView(ConfirmedAppointmentDialogView view) {
        this.view = view;
    }

    public void validate() {
        if (!validateReasonField(view.getReason()))
            return;
        sendRequest();
    }

    private boolean validateReasonField(String reason) {
        if (TextUtils.isEmpty(reason)) {
            view.showReasonError(view.context().getString(R.string.text_required_field));
            return false;
        }
        view.hideReasonError();
        return true;
    }

    private class DoctorConfirmObservable extends DefaultObserver<RedVetAppointment> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(RedVetAppointment redVetAppointment) {
            super.onNext(redVetAppointment);
            view.showErrorMessage("exitooo");
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.successRequest();
        }
    }
}
