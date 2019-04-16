package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UtilsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetNotificationList extends UseCase {

    private final UtilsRepository repository;

    @Inject
    GetNotificationList(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UtilsRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;

    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.listNotifications();
    }
}
