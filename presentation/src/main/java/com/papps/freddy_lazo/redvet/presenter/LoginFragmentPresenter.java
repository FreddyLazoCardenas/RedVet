package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorLogin;
import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.redvet.interfaces.LoginFragmentView;

import javax.inject.Inject;

import io.reactivex.Observer;

public class LoginFragmentPresenter implements Presenter<LoginFragmentView> {

    private final DoctorLogin doctorLogin;
    private LoginFragmentView view;

    @Inject
    LoginFragmentPresenter(DoctorLogin doctorLogin){
    this.doctorLogin = doctorLogin;
    }

    public void login(){
        doctorLogin.bindParams("richardisaac.pc92@gmail.com","123456");
        doctorLogin.execute(new LoginObservable());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void setView(LoginFragmentView view) {
        this.view = view;
    }

    private class LoginObservable extends DefaultObserver<Doctor> {

        @Override
        public void onNext(Doctor doctor) {
            super.onNext(doctor);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
