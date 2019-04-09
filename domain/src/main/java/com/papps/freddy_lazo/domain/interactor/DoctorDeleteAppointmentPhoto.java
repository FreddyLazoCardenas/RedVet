package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UtilsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DoctorDeleteAppointmentPhoto extends UseCase {

    private final UtilsRepository repository;
    private String apiToken;
    private int appointmentId;
    private int appointment_photo_id;

    @Inject
    DoctorDeleteAppointmentPhoto(UtilsRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(String apiToken, int appointmentId, int appointment_photo_id) {
        this.apiToken = apiToken;
        this.appointmentId = appointmentId;
        this.appointment_photo_id = appointment_photo_id;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.doctorDeleteAppointmentPhoto(apiToken, appointmentId, appointment_photo_id);
    }
}
