package com.papps.freddy_lazo.redvet.navigation;


import android.support.v4.app.FragmentManager;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;
import com.papps.freddy_lazo.redvet.view.activity.HomeActivity;
import com.papps.freddy_lazo.redvet.view.activity.LoginActivity;
import com.papps.freddy_lazo.redvet.view.activity.RegisterActivity;
import com.papps.freddy_lazo.redvet.view.fragment.MapFragment;
import com.papps.freddy_lazo.redvet.view.fragment.NewsFragment;
import com.papps.freddy_lazo.redvet.view.fragment.NotificationsFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ProfileFragment;
import com.papps.freddy_lazo.redvet.view.fragment.QuotesFragment;
import com.papps.freddy_lazo.redvet.view.fragment.RegisterFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ForgotPasswordFragment;
import com.papps.freddy_lazo.redvet.view.fragment.LoginFragment;
import com.papps.freddy_lazo.redvet.view.fragment.MainMenuFragment;
import com.papps.freddy_lazo.redvet.view.fragment.ServicesFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Navigator extends BaseNavigator {

    @Inject
    public Navigator() {
        //empty
    }

    //activities

    public void navigateToLoginActivity(BaseActivity activity) {
        activity.startActivity(LoginActivity.getCallingIntent(activity));
    }

    public void navigateToRegisterActivity(BaseActivity activity) {
        activity.startActivity(RegisterActivity.getCallingIntent(activity));
    }

    public void navigateToHomeActivity(BaseActivity activity) {
        activity.startActivity(HomeActivity.getCallingIntent(activity));
    }


    //fragments

    public void navigateToLoginFragment(BaseActivity activity) {
        fragmentTransaction(activity, LoginFragment.newInstance(), false);
    }

    public void navigateToRegisterFragment(BaseActivity activity) {
        fragmentTransaction(activity, RegisterFragment.newInstance(), false);
    }

    public void navigateToForgotPasswordFragment(BaseActivity activity) {
        fragmentTransaction(activity, ForgotPasswordFragment.newInstance(), true);
    }

    public void navigateToMainMenuFragment(BaseActivity activity) {
        fragmentTransaction(activity, MainMenuFragment.newInstance(), true);
    }

    public void navigateToServicesFragment(BaseActivity activity) {
        fragmentTransaction(activity, ServicesFragment.newInstance(), true);
    }

    public void navigateFromMenu(int itemId, BaseActivity activity) {
        activity.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        switch (itemId) {
            case R.id.action_quotes:
                fragmentTransaction(activity, QuotesFragment.newInstance(), false);
                break;
            case R.id.action_profile:
                fragmentTransaction(activity, ProfileFragment.newInstance(), false);
                break;
            case R.id.action_map:
                fragmentTransaction(activity, MapFragment.newInstance(), false);
                break;
            case R.id.action_news:
                fragmentTransaction(activity, NewsFragment.newInstance(), false);
                break;
            case R.id.action_notifications:
                fragmentTransaction(activity, NotificationsFragment.newInstance(), false);
                break;
            default:
                break;
        }
    }

    //Dialogs


}
