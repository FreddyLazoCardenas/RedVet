package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.redvet.model.RedVetDetailAppointmentModel;

public interface HomeActivityView extends BaseView {

    String getApiToken();

    void successRequest(RedVetDetailAppointmentModel data);
}
