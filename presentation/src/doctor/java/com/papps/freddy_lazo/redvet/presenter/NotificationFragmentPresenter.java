package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DeleteSpecificNotification;
import com.papps.freddy_lazo.domain.interactor.GetNotificationList;
import com.papps.freddy_lazo.domain.model.Notification;
import com.papps.freddy_lazo.redvet.interfaces.NotificationFragmentView;
import com.papps.freddy_lazo.redvet.model.mapper.NotificationModelMapper;

import java.util.List;

import javax.inject.Inject;


public class NotificationFragmentPresenter implements Presenter<NotificationFragmentView> {

    private final GetNotificationList getNotificationList;
    private final DeleteSpecificNotification deleteSpecificNotification;
    private NotificationFragmentView view;

    @Inject
    NotificationFragmentPresenter(GetNotificationList getNotificationList, DeleteSpecificNotification deleteSpecificNotification) {
        this.getNotificationList = getNotificationList;
        this.deleteSpecificNotification = deleteSpecificNotification;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void getNotificationList() {
        getNotificationList.execute(new NotificationsObservable());
    }

    public void deleteNotificationItem(Integer id) {
        deleteSpecificNotification.bindParams(id);
        deleteSpecificNotification.execute(new DeleteNotificationObservable());
    }

    @Override
    public void destroy() {
        getNotificationList.unsubscribe();
    }

    @Override
    public void setView(NotificationFragmentView view) {
        this.view = view;
    }

    private class NotificationsObservable extends DefaultObserver<List<Notification>> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<Notification> notifications) {
            super.onNext(notifications);
            view.successRequest(NotificationModelMapper.transform(notifications));
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }

    private class DeleteNotificationObservable extends DefaultObserver<Void> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.onSuccessComplete();
        }
    }

}
