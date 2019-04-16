package com.papps.freddy_lazo.redvet;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.papps.freddy_lazo.redvet.internal.dagger.component.ApplicationComponent;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerApplicationComponent;
import com.papps.freddy_lazo.redvet.internal.dagger.module.ApplicationModule;

import static com.papps.freddy_lazo.redvet.view.util.NotificationUtil.CHANNEL_ID;

public class AndroidApplication extends Application {

    private ApplicationComponent mApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
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

}
