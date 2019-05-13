package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DeletePetUseCase extends UseCase {

    private final UserRepository repository;
    private String apiToken;
    private int petLoverId;

    @Inject
    DeletePetUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, UserRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(String apiToken, int petLoverId) {
        this.apiToken = apiToken;
        this.petLoverId = petLoverId;

    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.deletePet(apiToken, petLoverId);
    }
}
