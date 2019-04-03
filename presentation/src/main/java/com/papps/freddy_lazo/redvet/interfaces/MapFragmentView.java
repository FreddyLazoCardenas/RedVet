package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.redvet.model.DoctorModel;

import java.util.List;

public interface MapFragmentView extends BaseView {

    void getListData(List<DoctorModel> data);
}
