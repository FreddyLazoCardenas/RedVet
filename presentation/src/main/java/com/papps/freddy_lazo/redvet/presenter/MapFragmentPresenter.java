package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.SearchDoctorLogin;
import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.redvet.interfaces.MapFragmentView;
import com.papps.freddy_lazo.redvet.model.mapper.DoctorModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MapFragmentPresenter implements Presenter<MapFragmentView> {

    private final SearchDoctorLogin searchDoctorLogin;
    private MapFragmentView view;

    @Inject
    MapFragmentPresenter(SearchDoctorLogin searchDoctorLogin) {
        this.searchDoctorLogin =searchDoctorLogin;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        searchDoctorLogin.unsubscribe();
    }

    public void getDoctors(){
        searchDoctorLogin.bindParams(new ArrayList<>(), new ArrayList<>(),new ArrayList<>() , "");
        searchDoctorLogin.execute(new SearchDoctorObservable());
    }

    @Override
    public void setView(MapFragmentView view) {
        this.view = view;
    }

    private class SearchDoctorObservable extends DefaultObserver<List<Doctor>> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(List<Doctor> doctors) {
            super.onNext(doctors);
           view.getListData(DoctorModelMapper.transform(doctors));
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
