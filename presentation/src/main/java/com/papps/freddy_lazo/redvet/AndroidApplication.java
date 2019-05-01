package com.papps.freddy_lazo.redvet;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import com.papps.freddy_lazo.redvet.internal.dagger.component.ApplicationComponent;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerApplicationComponent;
import com.papps.freddy_lazo.redvet.internal.dagger.module.ApplicationModule;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.util.NotificationUtil;

import static com.papps.freddy_lazo.redvet.view.util.NotificationUtil.CHANNEL_ID;

public class AndroidApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private ApplicationComponent mApplicationComponent;
    private boolean isAlive;
    private int startCount;
    private int stopCount;


    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        registerActivityLifecycleCallbacks(this);
        createNotificationChannel();
    }

    private void initializeInjector() {
        this.mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_notifications_name);
            String description = getString(R.string.app_notifications_content);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++startCount;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (activity instanceof HomeActivity) {
            NotificationUtil.dismissNotification(this);
            isAlive = true;
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        ++stopCount;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (activity instanceof HomeActivity) {
            NotificationUtil.dismissNotification(this);
            isAlive = false;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isBackground() {
        return startCount == stopCount;
    }
}
