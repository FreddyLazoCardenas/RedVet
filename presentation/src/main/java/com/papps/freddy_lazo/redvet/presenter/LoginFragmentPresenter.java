package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.redvet.interfaces.LoginFragmentView;

import javax.inject.Inject;

public class LoginFragmentPresenter implements Presenter<LoginFragmentView> {

    private LoginFragmentView view;

    @Inject
    LoginFragmentPresenter(){

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
}
