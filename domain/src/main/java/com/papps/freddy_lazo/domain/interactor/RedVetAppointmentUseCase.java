package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RedVetAppointmentUseCase extends UseCase {

    private final UserRepository userRepository;
    private String auth;
    private int appointmentId;

    @Inject
    RedVetAppointmentUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    public void bindParams(String auth, int appointmentId) {
        this.auth = auth;
        this.appointmentId = appointmentId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return userRepository.redVetAppointmentDetail(auth, appointmentId);
    }
}
