package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.RedVetAppointmentUseCase;
import com.papps.freddy_lazo.domain.model.RedVetDetailAppointment;
import com.papps.freddy_lazo.redvet.interfaces.HomeActivityView;
import com.papps.freddy_lazo.redvet.model.mapper.RedVetDetailAppointmentModelMapper;

import javax.inject.Inject;

public class HomeActivityPresenter implements Presenter<HomeActivityView> {

    private final RedVetAppointmentUseCase redVetAppointmentUseCase;
    private HomeActivityView view;

    @Inject
    HomeActivityPresenter(RedVetAppointmentUseCase redVetAppointmentUseCase) {
        this.redVetAppointmentUseCase = redVetAppointmentUseCase;
    }

    public void sendRequest(String auth, int appointment) {
        redVetAppointmentUseCase.bindParams(auth, appointment);
        redVetAppointmentUseCase.execute(new NotificationDetailObservable());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        redVetAppointmentUseCase.unsubscribe();
    }

    @Override
    public void setView(HomeActivityView view) {
        this.view = view;
    }

    private class NotificationDetailObservable extends DefaultObserver<RedVetDetailAppointment> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(RedVetDetailAppointment redVetDetailAppointment) {
            super.onNext(redVetDetailAppointment);
            view.successRequest(RedVetDetailAppointmentModelMapper.transform(redVetDetailAppointment));
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
        }
    }
}
