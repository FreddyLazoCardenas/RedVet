package com.papps.freddy_lazo.redvet.view.service;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.SaveNotification;
import com.papps.freddy_lazo.domain.model.Notification;
import com.papps.freddy_lazo.redvet.AndroidApplication;
import com.papps.freddy_lazo.redvet.view.util.NotificationUtil;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import java.util.Map;

public class FirebaseMessageService extends FirebaseMessagingService {

    //private SaveNotification saveNotification;
    private AndroidApplication mApp;
    private SaveNotification saveNotification;

    public FirebaseMessageService() {
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
<<<<<<< HEAD
        this.mApp = (AndroidApplication) getApplication();
        Map<String, String> data = remoteMessage.getData();
        Log.d("remoteMessage", remoteMessage.toString());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(), 0);
        NotificationUtil.showNotification(this, data.get("type"), data.get("message"), pendingIntent);
        String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        saveNotification= mApp.getApplicationComponent().saveNotification();
        saveNotification.bindParams(new Notification(data.get("type"), data.get("appointment_id"), data.get("message"), timeStamp, true));
        saveNotification.execute(new SaveDataObservable());
    }

    private class SaveDataObservable extends DefaultObserver<Void> {
        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

=======
        Map<String, String> data = remoteMessage.getData();
        Log.d("remoteMessage",remoteMessage.toString());
>>>>>>> 057eb87271ae0f817d700f2c43017bae9dee148a
    }
}
