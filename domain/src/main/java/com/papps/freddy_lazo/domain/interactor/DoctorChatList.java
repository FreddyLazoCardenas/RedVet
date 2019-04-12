package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UtilsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DoctorChatList extends UseCase {

    private final UtilsRepository repository;
    private String apiToken;
    private int appointmentId;

    @Inject
    DoctorChatList(UtilsRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(String apiToken, int appointmentId) {
        this.apiToken = apiToken;
        this.appointmentId = appointmentId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.doctorChat(apiToken, appointmentId);
    }
}
