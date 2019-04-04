package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.data.exception.RedVetException;
import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.ForgotPassword;
import com.papps.freddy_lazo.redvet.interfaces.ForgotPasswordFragmentView;

import javax.inject.Inject;


public class ForgotPasswordFragmentPresenter implements Presenter<ForgotPasswordFragmentView> {
    
    private final ForgotPassword forgotPassword;
    private ForgotPasswordFragmentView view;

    @Inject
    public ForgotPasswordFragmentPresenter(ForgotPassword forgotPassword) {
        this.forgotPassword = forgotPassword;
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
    public void setView(ForgotPasswordFragmentView view) {
        this.view = view;
    }

    public void recoverPassword() {
        forgotPassword.bindParams(view.getEmail());
        forgotPassword.execute(new ForgotPasswordObservable());
    }

    private class ForgotPasswordObservable extends DefaultObserver<Void> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            super.onError(e);
            RedVetException exception = (RedVetException) e;
            view.showErrorMessage(exception.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
