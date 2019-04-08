package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UtilsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DoctorAppointmentUseCase extends UseCase {


    private final UtilsRepository repository;
    private String apiToken;
    private String path ;

    @Inject
    DoctorAppointmentUseCase(UtilsRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(String apiToken) {
        this.apiToken = apiToken;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.doctorAppointments(apiToken);
    }
}
