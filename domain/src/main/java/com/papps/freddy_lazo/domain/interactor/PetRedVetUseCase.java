package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PetRedVetUseCase extends UseCase {

    private final UserRepository userRepository;

    @Inject
    PetRedVetUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(threadExecutor, postExecutionThread);
        this.userRepository=userRepository;
    }


    @Override
    protected Observable buildUseCaseObservable() {
        return userRepository.getPets();
    }
}
