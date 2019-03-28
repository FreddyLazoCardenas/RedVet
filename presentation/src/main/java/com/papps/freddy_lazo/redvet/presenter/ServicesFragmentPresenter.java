package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.DoctorServices;
import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.domain.model.Service;
import com.papps.freddy_lazo.redvet.interfaces.ServicesFragmentView;
import com.papps.freddy_lazo.redvet.model.mapper.ServicesModelMapper;

import java.util.List;

import javax.inject.Inject;

public class ServicesFragmentPresenter implements Presenter<ServicesFragmentView> {

    private final DoctorServices doctorServices;
    private ServicesFragmentView view;

    @Inject
    ServicesFragmentPresenter(DoctorServices doctorServices) {
        this.doctorServices = doctorServices;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void setView(ServicesFragmentView view) {
        this.view = view;
    }

    public void getServices() {
        doctorServices.execute(new ServicesObservable());
    }

    private class ServicesObservable extends DefaultObserver<List<Service>> {

        @Override
        public void onNext(List<Service> serviceList) {
            super.onNext(serviceList);
           view.listData(ServicesModelMapper.transform(serviceList));
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
