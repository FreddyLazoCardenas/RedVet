package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.redvet.model.DoctorAppointmentModel;

import java.util.List;

public interface AppointmentFragmentView extends LoadingView {


    String getApiToken();

    void successRequest(List<DoctorAppointmentModel> data);

    void successFinishRequest(int id);
}
