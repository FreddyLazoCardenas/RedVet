package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UtilsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PetLoverNews extends UseCase {

    private final UtilsRepository repository;
    private String path;

    @Inject
    PetLoverNews(UtilsRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(String path){
        this.path = path;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.petLoverNews(path);
    }
}
