package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CreateAppointmentUseCase extends UseCase {

    private final UserRepository repository;
    private String apiToken;
    private int doctor_id;
    private int pet_by_pet_lover_id;
    private String date;
    private String time;
    private String type;
    private String reason;

    @Inject
    CreateAppointmentUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(String apiToken, int doctor_id, int pet_by_pet_lover_id, String date, String time, String type, String reason) {
        this.apiToken = apiToken;
        this.doctor_id = doctor_id;
        this.pet_by_pet_lover_id = pet_by_pet_lover_id;
        this.date = date;
        this.time = time;
        this.type = type;
        this.reason = reason;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.createAppointment(apiToken, doctor_id, pet_by_pet_lover_id, date, time, type, reason);
    }
}
