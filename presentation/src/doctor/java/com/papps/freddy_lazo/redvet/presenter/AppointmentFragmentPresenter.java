package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorAppointmentFinish;
import com.papps.freddy_lazo.domain.interactor.DoctorAppointmentUseCase;
import com.papps.freddy_lazo.domain.model.DoctorAppointment;
import com.papps.freddy_lazo.domain.model.PetLoverAppointment;
import com.papps.freddy_lazo.domain.model.RedVetAppointment;
import com.papps.freddy_lazo.redvet.interfaces.AppointmentFragmentView;
import com.papps.freddy_lazo.redvet.model.mapper.DoctorAppointmentModelMapper;
import com.papps.freddy_lazo.redvet.model.mapper.PetLoverAppointmentModelMapper;

import java.util.List;

import javax.inject.Inject;

public class AppointmentFragmentPresenter implements Presenter<AppointmentFragmentView> {


    private final DoctorAppointmentUseCase appointment;
    private final DoctorAppointmentFinish doctorAppointmentFinish;
    private AppointmentFragmentView view;

    @Inject
    public AppointmentFragmentPresenter(DoctorAppointmentUseCase appointment, DoctorAppointmentFinish doctorAppointmentFinish) {
        this.appointment = appointment;
        this.doctorAppointmentFinish = doctorAppointmentFinish;
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

    public void sendFinishAppointmentRequest(int appointmentId) {
        doctorAppointmentFinish.bindParams(view.getApiToken(), appointmentId, "Finalizada via lista de citas");
        doctorAppointmentFinish.execute(new AppointmentFinishObservable());
    }

    @Override
    public void destroy() {
        appointment.unsubscribe();
        doctorAppointmentFinish.unsubscribe();
    }

    private class AppointmentFinishObservable extends DefaultObserver<RedVetAppointment> {
        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(RedVetAppointment redVetAppointment) {
            super.onNext(redVetAppointment);
            view.successFinishRequest(redVetAppointment.getId());
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

    @Override
    public void setView(AppointmentFragmentView view) {
        this.view = view;
    }

    private class AppointmentObservable extends DefaultObserver<List<DoctorAppointment>> {

        @Override
        protected void onStart() {
            super.onStart();
            view.showLoading();
        }


        @Override
        public void onNext(List<DoctorAppointment> appointment) {
            super.onNext(appointment);
            view.showErrorMessage("Éxito");
            view.successRequest(DoctorAppointmentModelMapper.transform(appointment));
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
