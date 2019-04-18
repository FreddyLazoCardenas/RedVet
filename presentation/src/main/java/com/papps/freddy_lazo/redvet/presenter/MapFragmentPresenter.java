package com.papps.freddy_lazo.redvet.presenter;

import com.papps.freddy_lazo.domain.interactor.DefaultObserver;
import com.papps.freddy_lazo.domain.interactor.PetRedVetUseCase;
import com.papps.freddy_lazo.domain.interactor.SearchDoctorLogin;
import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.domain.model.PetRedVet;
import com.papps.freddy_lazo.redvet.interfaces.MapFragmentView;
import com.papps.freddy_lazo.redvet.model.mapper.DoctorModelMapper;
import com.papps.freddy_lazo.redvet.model.mapper.PetRedVetModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MapFragmentPresenter implements Presenter<MapFragmentView> {

    private final SearchDoctorLogin searchDoctorLogin;
    private final PetRedVetUseCase petRedVetUseCase;
    private MapFragmentView view;

    @Inject
    MapFragmentPresenter(SearchDoctorLogin searchDoctorLogin, PetRedVetUseCase petRedVetUseCase) {
        this.searchDoctorLogin = searchDoctorLogin;
        this.petRedVetUseCase = petRedVetUseCase;
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
        petRedVetUseCase.unsubscribe();
    }

    public void getDoctors() {
        searchDoctorLogin.unsubscribe();
        searchDoctorLogin.bindParams(view.getType(), view.getServices(), view.getPets(), view.getText(), view.getApiToken());
        searchDoctorLogin.execute(new SearchDoctorObservable());
    }

    public void getPets(){
        petRedVetUseCase.execute(new PetsObservable());
    }

    private class PetsObservable extends DefaultObserver<List<PetRedVet>> {

        @Override
        protected void onStart() {
            super.onStart();
        }

        @Override
        public void onNext(List<PetRedVet> petRedVets) {
            super.onNext(petRedVets);
            view.successRequest(PetRedVetModelMapper.transform(petRedVets));
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
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
        public void onError(Throwable e) {
            super.onError(e);
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
