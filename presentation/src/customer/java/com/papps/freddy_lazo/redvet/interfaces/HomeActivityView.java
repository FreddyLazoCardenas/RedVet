package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.domain.model.RedVetNotification;
import com.papps.freddy_lazo.redvet.model.RedVetDetailAppointmentModel;
import com.papps.freddy_lazo.redvet.model.RedVetNotificationModel;

import java.util.List;

public interface HomeActivityView extends BaseView {

    String getApiToken();

    void successRequest(RedVetDetailAppointmentModel data);

    void successNotificationRequest(List<RedVetNotificationModel> data);
}
