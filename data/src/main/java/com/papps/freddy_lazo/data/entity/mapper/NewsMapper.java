package com.papps.freddy_lazo.data.entity.mapper;

import com.papps.freddy_lazo.data.entity.NewsEntity;
import com.papps.freddy_lazo.data.entity.ServicesEntity;
import com.papps.freddy_lazo.domain.model.News;
import com.papps.freddy_lazo.domain.model.Service;

import java.util.ArrayList;
import java.util.List;

public class NewsMapper {

    public NewsMapper() {
    }

    public static List<News> transform(List<NewsEntity> entity) {
        if (entity == null) {
            return null;
        }
        List<News> entities = new ArrayList<>();
        for (NewsEntity newsEntity : entity){
            entities.add(transform(newsEntity));
        }
        return entities;
    }

    private static News transform(NewsEntity entity){
        return new News(entity.getId(),entity.getTitle(),entity.getContent(),entity.getPhoto(),entity.getPhoto_url(),entity.getType());
    }
}
