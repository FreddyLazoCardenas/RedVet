package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorDeleteAppointmentPhoto;
import com.papps.freddy_lazo.redvet.interfaces.PetLoverAppointmentView;

import java.util.List;

import javax.inject.Inject;


public class PetLoverFinishedAppointmentPresenter implements Presenter<PetLoverAppointmentView> {

    private final DoctorDeleteAppointmentPhoto doctorDeleteAppointmentPhoto;
    private PetLoverAppointmentView view;

    @Inject
    public PetLoverFinishedAppointmentPresenter(DoctorDeleteAppointmentPhoto doctorDeleteAppointmentPhoto) {
        this.doctorDeleteAppointmentPhoto = doctorDeleteAppointmentPhoto;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void sendRequest() {
        doctorDeleteAppointmentPhoto.bindParams(view.getApiToken(), view.getAppointmentId(), view.getPhotoAppointmentId());
        doctorDeleteAppointmentPhoto.execute(new DeletePhotoObservable());
    }

    @Override
    public void destroy() {
        doctorDeleteAppointmentPhoto.unsubscribe();
    }

    @Override
    public void setView(PetLoverAppointmentView view) {
        this.view = view;
    }

    private class DeletePhotoObservable extends DefaultObserver<List<Void>> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.successDelete();
        }
    }
}
