package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.redvet.model.RedVetMessageModel;

import java.util.List;

public interface ChatActivityView extends BaseView {
    String getApiToken();

    int getAppointmentId();

    String getMessage();

    void successRequest(List<RedVetMessageModel> data);

    void successSendMessage(RedVetMessageModel data);
}
