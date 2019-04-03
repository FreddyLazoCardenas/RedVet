package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchDoctorLogin extends UseCase {

    private final UserRepository repository;
    private ArrayList<String> type;
    private ArrayList<Integer> services;
    private ArrayList<Integer> pets;
    private String text;

    @Inject
    SearchDoctorLogin(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(ArrayList<String> type, ArrayList<Integer> services, ArrayList<Integer> pets, String text) {
        this.type = type;
        this.services = services;
        this.pets = pets;
        this.text = text;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.searchDoctors(type, services, pets, text);
    }
}
