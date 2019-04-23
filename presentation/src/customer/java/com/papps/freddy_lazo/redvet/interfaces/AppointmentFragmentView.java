package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.redvet.model.DoctorAppointmentModel;
import com.papps.freddy_lazo.redvet.model.PetLoverAppointmentModel;

import java.util.List;

public interface AppointmentFragmentView extends LoadingView {


    String getApiToken();

    void successRequest(List<PetLoverAppointmentModel> data);
}
