package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UtilsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DoctorAppointmentFinish extends UseCase {

    private final UtilsRepository repository;
    private String apiToken;
    private int appointmentId;
    private String diagnosis;

    @Inject
    DoctorAppointmentFinish(UtilsRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(String apiToken, int appointmentId,String diagnosis) {
        this.apiToken = apiToken;
        this.appointmentId = appointmentId;
        this.diagnosis = diagnosis;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.doctorFinishAppointment(apiToken, appointmentId, diagnosis);
    }
}
