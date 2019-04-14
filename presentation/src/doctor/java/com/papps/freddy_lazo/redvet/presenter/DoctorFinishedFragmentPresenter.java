package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorDeleteAppointmentPhoto;
import com.papps.freddy_lazo.redvet.interfaces.DoctorFinishedFragmentView;

import java.util.List;

import javax.inject.Inject;


public class DoctorFinishedFragmentPresenter implements Presenter<DoctorFinishedFragmentView> {

    private final DoctorDeleteAppointmentPhoto doctorDeleteAppointmentPhoto;
    private DoctorFinishedFragmentView view;

    @Inject
    DoctorFinishedFragmentPresenter(DoctorDeleteAppointmentPhoto doctorDeleteAppointmentPhoto) {
        this.doctorDeleteAppointmentPhoto = doctorDeleteAppointmentPhoto;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void deletePhoto(){
        doctorDeleteAppointmentPhoto.bindParams(view.getApiToken(), view.getAppointmentId(),view.getPhotoId());
        doctorDeleteAppointmentPhoto.execute(new DeletePhotoObservable());
    }

    @Override
    public void destroy() {
        doctorDeleteAppointmentPhoto.unsubscribe();
    }

    @Override
    public void setView(DoctorFinishedFragmentView view) {
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
            view.successRequest();
        }
    }
}
