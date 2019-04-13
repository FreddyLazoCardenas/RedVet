package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorAppointmentConfirm;
import com.papps.freddy_lazo.domain.model.RedVetAppointment;
import com.papps.freddy_lazo.redvet.interfaces.PendingAppointmentDialogView;

import javax.inject.Inject;


public class DoctorPendingAppointmentPresenter implements Presenter<PendingAppointmentDialogView> {

    private final DoctorAppointmentConfirm doctorAppointmentConfirm;
    private PendingAppointmentDialogView view;

    @Inject
    DoctorPendingAppointmentPresenter(DoctorAppointmentConfirm doctorAppointmentConfirm) {
        this.doctorAppointmentConfirm = doctorAppointmentConfirm;
    }

    public void sendRequest(){
        doctorAppointmentConfirm.bindParams(view.getApiToken(), view.getAppointmentId());
        doctorAppointmentConfirm.execute(new DoctorConfirmObservable());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        doctorAppointmentConfirm.unsubscribe();
    }

    @Override
    public void setView(PendingAppointmentDialogView view) {
        this.view = view;
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
