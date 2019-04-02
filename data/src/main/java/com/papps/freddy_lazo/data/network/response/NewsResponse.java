package com.papps.freddy_lazo.data.network.response;

import com.google.gson.annotations.SerializedName;
import com.papps.freddy_lazo.data.entity.NewsEntity;
import com.papps.freddy_lazo.data.entity.ServicesEntity;

import java.util.List;

public class NewsResponse {

    @SerializedName("news")
    private List<NewsEntity> newsEntityList;

    public List<NewsEntity> getNewsEntityList() {
        return newsEntityList;
    }

    public void setNewsEntityList(List<NewsEntity> servicesEntityList) {
        this.newsEntityList = servicesEntityList;
    }
}
