package com.papps.freddy_lazo.redvet.interfaces;

import android.content.Context;

public interface BaseView {

    void initUI();

    Context context();

    void showErrorMessage(String message);

    void showErrorNetworkMessage(String message);
}
