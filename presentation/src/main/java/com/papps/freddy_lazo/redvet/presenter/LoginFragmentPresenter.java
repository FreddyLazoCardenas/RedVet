package com.papps.freddy_lazo.redvet.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.papps.freddy_lazo.data.entity.mapper.PetLoverLoginMapper;
import com.papps.freddy_lazo.data.sharedPreferences.PreferencesManager;
import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorLogin;
import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.domain.model.PetLover;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.LoginFragmentView;
import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.model.PetLoverModel;
import com.papps.freddy_lazo.redvet.model.mapper.DoctorModelMapper;
import com.papps.freddy_lazo.redvet.model.mapper.PetLoverModelMapper;

import javax.inject.Inject;

public class LoginFragmentPresenter implements Presenter<LoginFragmentView> {

    private final DoctorLogin doctorLogin;
    private final PreferencesManager preferencesManager;
    private LoginFragmentView view;

    @Inject
    LoginFragmentPresenter(PreferencesManager preferencesManager,DoctorLogin doctorLogin) {
        this.doctorLogin = doctorLogin;
        this.preferencesManager = preferencesManager;
    }

    private void login() {
        doctorLogin.bindParams(view.getEmail(), view.getPassword(), view.getFlavor(),view.getFcmToken());
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
            view.showLoading();
        }

        @Override
        public void onNext(Doctor doctor) {
            super.onNext(doctor);
            DoctorModel doctorModel = DoctorModelMapper.transform(doctor);
            preferencesManager.saveDoctorCurrentUser(doctorModel.toString());
            Log.d("onNext", "next doctor");
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.hideLoading();
            if (e instanceof NullPointerException) {
                view.showErrorMessage("Error esta cuenta es de petLover");
                return;
            }
            view.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.hideLoading();
            view.successLogin();
        }
    }

    private class PetLoverLoginObservable extends DefaultObserver<PetLover> {
        @Override
        protected void onStart() {
            super.onStart();
            view.showLoading();
        }

        @Override
        public void onNext(PetLover petLover) {
            super.onNext(petLover);
            PetLoverModel petLoverModel = PetLoverModelMapper.transform(petLover);
            preferencesManager.savePetLoverCurrentUser(petLoverModel.toString());
            Log.d("onNext", "next petLover");
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            if (e instanceof NullPointerException) {
                view.showErrorMessage("Error esta cuenta es de doctor");
                return;
            }
            view.showErrorMessage(e.getMessage());
            view.hideLoading();
        }

        @Override
        public void onComplete() {
            super.onComplete();
            view.hideLoading();
            view.successLogin();
        }

    }
}
