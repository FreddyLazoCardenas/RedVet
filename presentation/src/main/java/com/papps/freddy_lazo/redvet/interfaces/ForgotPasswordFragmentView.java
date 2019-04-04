package com.papps.freddy_lazo.redvet.interfaces;

public interface ForgotPasswordFragmentView extends BaseView {

    String getEmail();

    void showEmailError(String string);

    void hideEmailError();

    void successRequest();
}
