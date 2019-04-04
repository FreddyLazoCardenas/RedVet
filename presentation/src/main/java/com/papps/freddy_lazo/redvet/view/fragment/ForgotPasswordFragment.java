package com.papps.freddy_lazo.redvet.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.ForgotPasswordFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerForgotPasswordFragmentComponent;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerLoginFragmentComponent;
import com.papps.freddy_lazo.redvet.presenter.ForgotPasswordFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.LoginActivity;
import com.papps.freddy_lazo.redvet.view.activity.RegisterActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgotPasswordFragment extends BaseFragment implements ForgotPasswordFragmentView {

    @Inject
    ForgotPasswordFragmentPresenter presenter;

    @BindView(R.id.et_email)
    EditText etEmail;
    private LoginActivity activity;

    public static Fragment newInstance() {
        return new ForgotPasswordFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (LoginActivity) getActivity();
        initUI();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    private void buildInjection() {
        DaggerForgotPasswordFragmentComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }


    @Override
    public void initUI() {
        presenter.setView(this);
    }

    @Override
    public Context context() {
        return activity;
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    @OnClick(R.id.button)
    public void button() {
        presenter.recoverPassword();
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }
}
