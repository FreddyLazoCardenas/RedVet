package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchDoctorLogin extends UseCase {

    private final UserRepository repository;
    private List<String> type;
    private List<Integer> services;
    private List<Integer> pets;
    private String text;
    private String apiToken;

    @Inject
    SearchDoctorLogin(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(List<String> type, List<Integer> services, List<Integer> pets, String text, String apiToken) {
        this.type = type;
        this.services = services;
        this.pets = pets;
        this.text = text;
        this.apiToken = apiToken;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.searchDoctors(type, services, pets, text, apiToken);
    }
}
