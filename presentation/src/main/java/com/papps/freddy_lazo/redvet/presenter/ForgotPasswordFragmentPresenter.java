package com.papps.freddy_lazo.redvet.presenter;

import android.text.TextUtils;

import com.papps.freddy_lazo.data.exception.RedVetException;
import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.ForgotPassword;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.interfaces.ForgotPasswordFragmentView;

import java.util.List;

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

    private void recoverPassword() {
        forgotPassword.bindParams(view.getEmail());
        forgotPassword.execute(new ForgotPasswordObservable());
    }

    public void validation() {
        if (!isValidEmail(view.getEmail()))
            return;

        recoverPassword();
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

    private class ForgotPasswordObservable extends DefaultObserver<List<Void>> {

        @Override
        protected void onStart() {
            super.onStart();
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
            view.successRequest();
        }
    }
}
