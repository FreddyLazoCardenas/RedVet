package com.papps.freddy_lazo.redvet.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.papps.freddy_lazo.data.exception.RedVetException;
import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorLogin;
import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.domain.model.PetLover;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.LoginFragmentView;

import javax.inject.Inject;

public class LoginFragmentPresenter implements Presenter<LoginFragmentView> {

    private final DoctorLogin doctorLogin;
    private LoginFragmentView view;

    @Inject
    LoginFragmentPresenter(DoctorLogin doctorLogin) {
        this.doctorLogin = doctorLogin;
    }

    private void login() {
        doctorLogin.bindParams(view.getEmail(), view.getPassword(), view.getFlavor());
        if (view.getFlavor().equals("doctor"))
            doctorLogin.execute(new DoctorLoginObservable());
        else
            doctorLogin.execute(new PetLoverLoginObservable());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        doctorLogin.unsubscribe();
    }

    @Override
    public void setView(LoginFragmentView view) {
        this.view = view;
    }

    public void validation() {
        if (!isValidEmail(view.getEmail()))
            return;
        if (!isValidPassword(view.getPassword()))
            return;
        login();
    }

    private boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            view.showEmailError(view.context().getString(R.string.text_required_field));
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showEmailError(view.context().getString(R.string.text_bad_format_field));
            return false;
        }
        view.hideEmailError();
        return true;
    }

    private boolean isValidPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            view.showPasswordError(view.context().getString(R.string.text_required_field));
            return false;
        }
        if (password.length() < 6) {
            view.showPasswordError(view.context().getString(R.string.text_bad_format_field));
            return false;
        }
        view.hidePasswordError();
        return true;
    }


    private class DoctorLoginObservable extends DefaultObserver<Doctor> {
        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(Doctor doctor) {
            super.onNext(doctor);
            Log.d("onNext","next doctor");
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            RedVetException exception = (RedVetException) e;
            view.showErrorMessage(exception.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.successLogin();
        }
    }

    private class PetLoverLoginObservable extends DefaultObserver<PetLover> {
        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(PetLover petLover) {
            super.onNext(petLover);
            Log.d("onNext","next petLover");
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            RedVetException exception = (RedVetException) e;
            view.showErrorMessage(exception.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.successLogin();
        }

    }
}
