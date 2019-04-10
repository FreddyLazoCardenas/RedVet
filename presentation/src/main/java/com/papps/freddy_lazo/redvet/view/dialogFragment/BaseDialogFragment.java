package com.papps.freddy_lazo.redvet.view.dialogFragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.papps.freddy_lazo.redvet.AndroidApplication;
import com.papps.freddy_lazo.redvet.navigation.Navigator;
import com.papps.freddy_lazo.redvet.view.activity.BaseActivity;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseDialogFragment extends DialogFragment {

    @Inject
    Navigator navigator;

    private Unbinder mUnbinder;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(getDialog().getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        injectView(view);
    }

    private void injectView(View view) {
        ButterKnife.setDebug(true);
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    protected AndroidApplication getAndroidApplication() {
        return (AndroidApplication) getActivity().getApplication();
    }

    protected void showMessage(BaseActivity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

}
