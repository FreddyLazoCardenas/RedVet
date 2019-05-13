package com.papps.freddy_lazo.redvet.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.internal.bus.event.Event;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerHomeComponent;
import com.papps.freddy_lazo.redvet.model.DoctorModel;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNav;
    @Inject
    PreferencesManager preferencesManager;

    public static Intent getCallingIntent(BaseActivity activity) {
        return new Intent(activity, HomeActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        injectView(this);
        buildInjection();
        Log.d("date", System.currentTimeMillis() + "");
        initUI();
    }

    private void buildInjection() {
        DaggerHomeComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    public void initUI() {
        bottomNav.getMenu().removeItem(R.id.action_map);
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.action_quotes);

    }

    @Override
    public void onStart() {
        super.onStart();
        subscribeBus();
    }

    @Override
    public void onStop() {
        super.onStop();
        unsubscribeBus();
    }

    @Override
    protected Consumer<Object> getBusAction() {
        return event -> {
            if (event instanceof Event.NotificationEvent) {
                Log.d("getBusAction", "llego a la actividad");
            } else if (event instanceof Event.NotificationChatEvent) {
                Event.NotificationChatEvent response = (Event.NotificationChatEvent) event;
                navigator.navigateToChatActivity(this, response.getAppointmentId());
            }
        };
    }


    public DoctorModel getModel() {
        return DoctorModel.toModel(preferencesManager.getDoctorCurrentUser());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_quotes:
                navigator.navigateFromMenu(R.id.action_quotes, this);
                return true;
            case R.id.action_profile:
                navigator.navigateFromMenu(R.id.action_profile, this);
                return true;
            case R.id.action_news:
                navigator.navigateFromMenu(R.id.action_news, this);
                return true;
            case R.id.action_notifications:
                navigator.navigateFromMenu(R.id.action_notifications, this);
                return true;
            default:
                return false;
        }
    }
}
