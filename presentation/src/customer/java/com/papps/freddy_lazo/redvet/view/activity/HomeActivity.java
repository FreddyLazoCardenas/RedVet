package com.papps.freddy_lazo.redvet.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.HomeActivityView;
import com.papps.freddy_lazo.redvet.internal.bus.Event;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerHomeComponent;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.RedVetDetailAppointmentModel;
import com.papps.freddy_lazo.redvet.model.RedVetNotificationModel;
import com.papps.freddy_lazo.redvet.presenter.HomeActivityPresenter;


import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, HomeActivityView {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNav;
    @Inject
    PreferencesManager preferencesManager;
    @Inject
    HomeActivityPresenter presenter;

    private View badge;

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
        subscribeBus();
        notificationBadge();
        initUI();
    }

    private void buildInjection() {
        DaggerHomeComponent.builder().applicationComponent(getApplicationComponent()).build().inject(this);
    }

    @Override
    public void initUI() {
        presenter.setView(this);
        presenter.getNotificationList();
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.action_map);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribeBus();
    }

    private void notificationBadge() {
        BottomNavigationMenuView bottomNavigationMenuView =
                (BottomNavigationMenuView) bottomNav.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(4);
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;
        badge = LayoutInflater.from(this).inflate(R.layout.badge_bottom_nav_view, itemView, true).findViewById(R.id.badge);
    }

    @Override
    protected Consumer<Object> getBusAction() {
        return event -> {
            if (event instanceof Event.NotificationEvent) {
                Event.NotificationEvent response = (Event.NotificationEvent) event;
                presenter.sendRequest(getApiToken(), response.getAppointmentId());
                Log.d("getBusAction", "llego a la actividad");
            } else if (event instanceof Event.NotificationChatEvent) {
                Event.NotificationChatEvent response = (Event.NotificationChatEvent) event;
                navigator.navigateToChatActivity(this, response.getAppointmentId());
            } else if (event instanceof Event.SuccessAppointment) {
                navigator.navigateSuccessAppointment(this);
            }
        };
    }


    public PetLoverModel getModel() {
        Log.d("testraro", PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser()).toString());
        return PetLoverModel.toModel(preferencesManager.getPetLoverCurrentUser());
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
            case R.id.action_map:
                navigator.navigateFromMenu(R.id.action_map, this);
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

    @Override
    public String getApiToken() {
        return getModel().getApi_token();
    }

    @Override
    public void successRequest(RedVetDetailAppointmentModel data) {
        switch (data.getStatus()) {
            case "confirmed":
                navigator.navigateDoctorConfirmedAppointmentFragment(this, data.toString());
                break;
            case "finished":
                navigator.navigateDoctorFinishedAppointmentFragment(this, data.toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void successNotificationRequest(List<RedVetNotificationModel> data) {
        if (data.isEmpty()) {
            badge.setVisibility(View.GONE);
            return;
        }
        for (RedVetNotificationModel model : data) {
            if (!model.isRead()) {
                badge.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    public View getBadge() {
        return badge;
    }
}
