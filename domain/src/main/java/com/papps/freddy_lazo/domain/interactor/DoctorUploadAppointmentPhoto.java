package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UtilsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DoctorUploadAppointmentPhoto extends UseCase {

    private final UtilsRepository repository;
    private String apiToken;
    private int appointmentId;
    private byte[] photo;

    @Inject
    DoctorUploadAppointmentPhoto(UtilsRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(String apiToken, int appointmentId, byte[] photo) {
        this.apiToken = apiToken;
        this.appointmentId = appointmentId;
        this.photo = photo;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.doctorUploadAppointmentPhoto(apiToken, appointmentId, photo);
    }
}
