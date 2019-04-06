package com.papps.freddy_lazo.domain.interactor;

import com.papps.freddy_lazo.domain.executor.PostExecutionThread;
import com.papps.freddy_lazo.domain.executor.ThreadExecutor;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PetLoverUpdate extends UseCase {

    private final UserRepository repository;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String dni;
    private String address;
    private String phone;
    private String fcmToken;
    private List<PetRegister> pets;
    private String photo;
    private String apiToken;

    @Inject
    PetLoverUpdate(UserRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public void bindParams(String apiToken ,String email, String password, String firstName, String lastName, String dni, String address, String phone, String photo, String fcmToken, List<PetRegister> pets) {
        this.apiToken = apiToken;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
        this.fcmToken = fcmToken;
        this.pets = pets;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.updatePetLover(apiToken,email, password, firstName, lastName, dni, address, phone, photo, fcmToken, pets);
    }
}
