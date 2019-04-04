package com.papps.freddy_lazo.redvet.interfaces;

public interface LoginFragmentView extends BaseView {

    String getEmail();

    String getFlavor();

    String getPassword();

    void showEmailError(String string);

    void hideEmailError();

    void showPasswordError(String string);

    void hidePasswordError();

    void successLogin();
}
