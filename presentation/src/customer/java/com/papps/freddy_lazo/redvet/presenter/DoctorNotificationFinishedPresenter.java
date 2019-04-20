package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.PetLoverQualifyAppointment;
import com.papps.freddy_lazo.domain.model.PetLoverAppointment;
import com.papps.freddy_lazo.redvet.interfaces.DoctorNotificationFinishedView;

import javax.inject.Inject;


public class DoctorNotificationFinishedPresenter implements Presenter<DoctorNotificationFinishedView> {

    private final PetLoverQualifyAppointment petLoverQualifyAppointment;
    private DoctorNotificationFinishedView view;

    @Inject
    DoctorNotificationFinishedPresenter(PetLoverQualifyAppointment petLoverQualifyAppointment) {
        this.petLoverQualifyAppointment = petLoverQualifyAppointment;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void sendRequest() {
        petLoverQualifyAppointment.bindParams(view.getApiToken(), view.getAppointmentId(), view.getQualification());
        petLoverQualifyAppointment.execute(new QualifyDoctorObservable());
    }

    @Override
    public void setView(DoctorNotificationFinishedView view) {
        this.view = view;
    }

    private class QualifyDoctorObservable extends DefaultObserver<PetLoverAppointment> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(PetLoverAppointment petLoverAppointment) {
            super.onNext(petLoverAppointment);
            view.successRequest();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
