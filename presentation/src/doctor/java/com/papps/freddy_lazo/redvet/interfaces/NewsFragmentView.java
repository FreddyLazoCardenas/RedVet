package com.papps.freddy_lazo.redvet.interfaces;

import com.papps.freddy_lazo.redvet.model.NewsModel;

import java.util.List;

public interface NewsFragmentView extends LoadingView {

    void listData(List<NewsModel> transform);

    String getApiToken();

}
