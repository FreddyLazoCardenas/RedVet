package com.papps.freddy_lazo.data.repository;

import com.papps.freddy_lazo.data.entity.mapper.ServicesMapper;
import com.papps.freddy_lazo.data.network.RestApi;
import com.papps.freddy_lazo.domain.model.Service;
import com.papps.freddy_lazo.domain.repository.UtilsRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class UtilsDataRepository implements UtilsRepository {

    private final RestApi mRestApi;

    @Inject
    UtilsDataRepository(RestApi mRestApi) {
        this.mRestApi = mRestApi;
    }

    @Override
    public Observable<List<Service>> services() {
        return mRestApi.services().map(ServicesMapper::transform);
    }
}
