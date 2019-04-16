package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.model.Notification;
import com.papps.freddy_lazo.domain.repository.UtilsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SaveNotification extends UseCase {

    private final UtilsRepository repository;
    private Notification notification;

    @Inject
     SaveNotification(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UtilsRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(Notification notification){
        this.notification = notification;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.saveNotification(notification);
    }
}
