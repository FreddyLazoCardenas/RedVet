package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PetLoverQualifyAppointment extends UseCase {

    private final UserRepository userRepository;
    private String auth;
    private int qualification;
    private int appointmentId;

    @Inject
    PetLoverQualifyAppointment(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    public void bindParams(String auth, int appointmentId, int qualification) {
        this.auth = auth;
        this.appointmentId = appointmentId;
        this.qualification = qualification;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return userRepository.petLoverQualifyAppointment(auth, appointmentId, qualification);
    }
}
