package com.papps.freddy_lazo.redvet.model.mapper;

import com.papps.freddy_lazo.domain.model.News;
import com.papps.freddy_lazo.redvet.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class NewsModelMapper {

    public NewsModelMapper() {
    }

    public static List<NewsModel> transform(List<News> newsList) {
        List<NewsModel> newsModelList = new ArrayList<>();
        if (newsList == null)
            return newsModelList;
        for (News news : newsList) {
            newsModelList.add(transform(news));
        }
        return newsModelList;
    }

    private static NewsModel transform(News news) {
        return new NewsModel(news.getId(), news.getTitle(), news.getContent(), news.getPhoto(), news.getPhoto_url(), news.getType());
    }
}
