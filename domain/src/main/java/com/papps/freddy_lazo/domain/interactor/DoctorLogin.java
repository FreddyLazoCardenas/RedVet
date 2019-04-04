package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DoctorLogin extends UseCase {

    private final UserRepository repository;
    private String email;
    private String password;
    private String flavor;

    @Inject
    DoctorLogin(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(String email, String password, String flavor) {
        this.email = email;
        this.password = password;
        this.flavor = flavor;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        if (flavor.equals("doctor"))
            return repository.loginDoctor(email, password);
        else
            return repository.loginPetLover(email, password);

    }
}
