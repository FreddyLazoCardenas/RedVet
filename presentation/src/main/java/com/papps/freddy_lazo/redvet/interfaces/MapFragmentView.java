package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.redvet.model.DoctorModel;
import com.papps.freddy_lazo.redvet.model.PetRedVetModel;

import java.util.ArrayList;
import java.util.List;

public interface MapFragmentView extends BaseView {

    void getListData(List<DoctorModel> data);

    String getApiToken();

    List<String> getType();

    List<Integer>  getServices();

    List<Integer> getPets();

    String getText();

    void successRequest(List<PetRedVetModel> data);
}
