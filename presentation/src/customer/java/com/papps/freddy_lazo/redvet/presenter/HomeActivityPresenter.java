package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.RedVetAppointmentUseCase;
import com.papps.freddy_lazo.domain.interactor.RedVetNotifications;
import com.papps.freddy_lazo.domain.model.RedVetDetailAppointment;
import com.papps.freddy_lazo.domain.model.RedVetNotification;
import com.papps.freddy_lazo.redvet.interfaces.HomeActivityView;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.mapper.NotificationModelMapper;
import com.papps.freddy_lazo.redvet.model.mapper.RedVetDetailAppointmentModelMapper;

import java.util.List;

import javax.inject.Inject;

public class HomeActivityPresenter implements Presenter<HomeActivityView> {

    private final RedVetAppointmentUseCase redVetAppointmentUseCase;
    private final RedVetNotifications redVetNotification;
    private final PreferencesManager preferencesManager;
    private HomeActivityView view;

    @Inject
    HomeActivityPresenter(PreferencesManager preferencesManager, RedVetAppointmentUseCase redVetAppointmentUseCase, RedVetNotifications redVetNotification) {
        this.redVetAppointmentUseCase = redVetAppointmentUseCase;
        this.redVetNotification = redVetNotification;
        this.preferencesManager = preferencesManager;
    }

    public void sendRequest(String auth, int appointment) {
        redVetAppointmentUseCase.bindParams(auth, appointment);
        redVetAppointmentUseCase.execute(new NotificationDetailObservable());
    }

    public void getNotificationList() {
        redVetNotification.bindParams(getApiToken());
        redVetNotification.execute(new NotificationsObservable());
    }

    private String getApiToken() {
        return PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser()).getApi_token();
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
        redVetNotification.unsubscribe();
    }

    @Override
    public void setView(HomeActivityView view) {
        this.view = view;
    }

    private class NotificationsObservable extends DefaultObserver<List<RedVetNotification>> {

        @Override
        public void onNext(List<RedVetNotification> notifications) {
            super.onNext(notifications);
            view.successNotificationRequest(NotificationModelMapper.transform(notifications));
        }

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
