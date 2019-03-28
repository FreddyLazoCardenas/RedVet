package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.redvet.model.ServicesModel;

import java.util.List;

public interface ServicesFragmentView extends BaseView {

    void listData(List<ServicesModel> data);
}
