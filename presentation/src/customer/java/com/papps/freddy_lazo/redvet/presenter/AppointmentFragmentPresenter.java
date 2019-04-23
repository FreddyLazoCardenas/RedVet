package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.PetLoverAppointmentUseCase;
import com.papps.freddy_lazo.domain.model.PetLoverAppointment;
import com.papps.freddy_lazo.redvet.interfaces.AppointmentFragmentView;
import com.papps.freddy_lazo.redvet.model.mapper.PetLoverAppointmentModelMapper;

import java.util.List;

import javax.inject.Inject;

public class AppointmentFragmentPresenter implements Presenter<AppointmentFragmentView> {


    private final PetLoverAppointmentUseCase appointment;
    private AppointmentFragmentView view;

    @Inject
    public AppointmentFragmentPresenter(PetLoverAppointmentUseCase appointment) {
        this.appointment = appointment;
    }

    public void sendRequest() {
        appointment.bindParams(view.getApiToken());
        appointment.execute(new AppointmentObservable());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        appointment.unsubscribe();
    }

    @Override
    public void setView(AppointmentFragmentView view) {
        this.view = view;
    }

    private class AppointmentObservable extends DefaultObserver<List<PetLoverAppointment>> {

        @Override
        protected void onStart() {
            super.onStart();
            view.showLoading();
        }


        @Override
        public void onNext(List<PetLoverAppointment> appointment) {
            super.onNext(appointment);
            view.showErrorMessage("exitoooo");
            view.successRequest(PetLoverAppointmentModelMapper.transform(appointment));
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
            view.hideLoading();
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.hideLoading();
        }
    }
}
