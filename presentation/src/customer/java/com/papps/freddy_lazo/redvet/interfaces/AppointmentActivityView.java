package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.redvet.model.AppointmentModel;
import com.papps.freddy_lazo.redvet.model.CreateAppointmentModel;

public interface AppointmentActivityView extends BaseView {

    void successResponse(CreateAppointmentModel data);

    String getApiToken();

    int doctorId();

    Integer petByPetLoverId();

    String getDate();

    String getTime();

    String getType();

    String getReason();
}
