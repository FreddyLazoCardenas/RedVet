package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.redvet.model.NotificationModel;

import java.util.List;

public interface NotificationFragmentView extends BaseView {
    void successRequest(List<NotificationModel> transform);
}
