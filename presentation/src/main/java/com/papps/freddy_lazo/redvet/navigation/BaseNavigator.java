package com.papps.freddy_lazo.redvet.navigation;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;

public abstract class BaseNavigator {

    BaseNavigator() {
        //empty
    }


    public void navigateToExternalBrowser(BaseActivity activity, String url) {
        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    void fragmentTransaction(BaseActivity activity, FragmentTransaction transaction) {
        if (activity.isStopped()) transaction.commitAllowingStateLoss();
        else transaction.commit();
    }

    void fragmentTransaction(BaseActivity activity, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) transaction.addToBackStack(fragment.getClass().getSimpleName());
        if (activity.isStopped()) transaction.commitAllowingStateLoss();
        else transaction.commit();
    }

    void fragmentTransactionClearBackStack(BaseActivity activity, Fragment fragment){
        FragmentManager manager = activity.getSupportFragmentManager();
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction(activity,fragment,false);
    }

    void dialogTransaction(BaseActivity activity, DialogFragment dialogFragment) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.add(dialogFragment, dialogFragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }

}
