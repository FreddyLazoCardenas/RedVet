package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DeleteSpecificNotification;
import com.papps.freddy_lazo.domain.interactor.GetNotificationList;
import com.papps.freddy_lazo.domain.interactor.RedVetDeleteNotification;
import com.papps.freddy_lazo.domain.interactor.RedVetNotifications;
import com.papps.freddy_lazo.domain.interactor.RedVetReadNotification;
import com.papps.freddy_lazo.domain.model.Notification;
import com.papps.freddy_lazo.domain.model.RedVetNotification;
import com.papps.freddy_lazo.redvet.interfaces.NotificationFragmentView;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.mapper.NotificationModelMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;


public class NotificationFragmentPresenter implements Presenter<NotificationFragmentView> {

    private final RedVetNotifications redVetNotification;
    private final PreferencesManager preferencesManager;
    private final RedVetReadNotification redVetReadNotification;
    private final RedVetDeleteNotification redVetDeleteNotification;
    private NotificationFragmentView view;

    @Inject
    NotificationFragmentPresenter(PreferencesManager preferencesManager, RedVetNotifications redVetNotification, RedVetReadNotification redVetReadNotification, RedVetDeleteNotification redVetDeleteNotification) {
        this.redVetNotification = redVetNotification;
        this.redVetReadNotification = redVetReadNotification;
        this.redVetDeleteNotification = redVetDeleteNotification;
        this.preferencesManager = preferencesManager;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }


    public void getNotificationList() {
        redVetNotification.bindParams(getApiToken());
        redVetNotification.execute(new NotificationsObservable());
    }

    private String getApiToken() {
        return PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser()).getApi_token();
    }

    @Override
    public void destroy() {
        redVetNotification.unsubscribe();
        redVetReadNotification.unsubscribe();
        redVetDeleteNotification.unsubscribe();
    }

    @Override
    public void setView(NotificationFragmentView view) {
        this.view = view;
    }

    public void markReadNotificationItem(Integer id) {
        redVetReadNotification.bindParams(getApiToken(), id);
        redVetReadNotification.execute(new ReadNotificationObservable());
    }

    public void deleteNotificationItem(Integer id) {
        redVetDeleteNotification.bindParams(getApiToken(), id);
        redVetDeleteNotification.execute(new DeleteNotificationObservable());
    }

    private class NotificationsObservable extends DefaultObserver<List<RedVetNotification>> {

        @Override
        protected void onStart() {
            super.onStart();
            view.showLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
            view.hideLoading();
        }

        @Override
        public void onNext(List<RedVetNotification> notifications) {
            super.onNext(notifications);
            view.successRequest(NotificationModelMapper.transform(notifications));
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.hideLoading();
        }
    }

    private class ReadNotificationObservable extends DefaultObserver<RedVetNotification> {
        @Override
        protected void onStart() {
            super.onStart();
            view.showLoading();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
            view.hideLoading();
        }

        @Override
        public void onNext(RedVetNotification notifications) {
            super.onNext(notifications);
            view.successReadRequest(NotificationModelMapper.transform(notifications));
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.hideLoading();
        }
    }


    private class DeleteNotificationObservable extends DefaultObserver<List<Void>> {
        @Override
        protected void onStart() {
            super.onStart();
            view.showLoading();
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
            view.onSuccessDelete();
        }
    }
}
