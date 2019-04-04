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
import android.widget.TextView;
import android.widget.Toast;

import com.papps.freddy_lazo.redvet.BuildConfig;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.LoginFragmentView;
import com.papps.freddy_lazo.redvet.internal.dagger.component.DaggerLoginFragmentComponent;
import com.papps.freddy_lazo.redvet.presenter.LoginFragmentPresenter;
import com.papps.freddy_lazo.redvet.view.activity.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginFragmentView {

    @Inject
    LoginFragmentPresenter presenter;


    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;

    private LoginActivity activity;

    public static Fragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildInjection();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    private void buildInjection() {
        DaggerLoginFragmentComponent.builder()
                .applicationComponent(getAndroidApplication().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (LoginActivity) getActivity();
        initUI();
    }

    @OnClick(R.id.forgot_password)
    public void forgotPassword() {
        navigator.navigateToForgotPasswordFragment(activity);
    }

    @OnClick(R.id.register_text)
    public void registerUser() {
        navigator.navigateToRegisterActivity(activity);
    }

    @OnClick(R.id.button_login)
    public void login() {
        presenter.validation();
    }

    @Override
    public void initUI() {
        presenter.setView(this);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorNetworkMessage(String message) {

    }

    @Override
    public String getEmail() {
        return etUsername.getText().toString();
    }

    @Override
    public String getFlavor() {
        return BuildConfig.FLAVOR;
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void showEmailError(String message) {
        showEtError(etUsername, message);
    }

    @Override
    public void hideEmailError() {
        hideEtError(etUsername);
    }

    @Override
    public void showPasswordError(String message) {
        showEtError(etPassword, message);
    }

    @Override
    public void hidePasswordError() {
        hideEtError(etPassword);
    }

    @Override
    public void successLogin() {
        navigator.navigateToHomeActivity(activity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    private void hideEtError(EditText editText) {
        editText.setError(null);
    }

    private void showEtError(EditText editText, String message) {
        editText.setError(message);
        editText.requestFocus();
    }
}
