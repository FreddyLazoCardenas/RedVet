package com.papps.freddy_lazo.domain.repository;

import com.papps.freddy_lazo.domain.model.News;
import com.papps.freddy_lazo.domain.model.Service;

import java.util.List;

import io.reactivex.Observable;

public interface UtilsRepository {

    Observable<List<Service>> services();


    Observable<List<News>> petLoverNews();

}
