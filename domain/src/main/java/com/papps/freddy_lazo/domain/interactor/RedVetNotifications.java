package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RedVetNotifications extends UseCase {

    private final UserRepository repository;
    private String apiToken;

    @Inject
    RedVetNotifications(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(String apiToken){
        this.apiToken = apiToken;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.redVetNotifications(apiToken);
    }
}
