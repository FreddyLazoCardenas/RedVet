package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.redvet.model.RedVetNotificationModel;

import java.util.List;

public interface NotificationFragmentView extends BaseView {
    void successRequest(List<RedVetNotificationModel> data);

    void successReadRequest(RedVetNotificationModel data);

    void showLoading();

    void hideLoading();
}
