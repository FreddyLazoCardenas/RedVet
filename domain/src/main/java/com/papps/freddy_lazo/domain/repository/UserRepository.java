package com.papps.freddy_lazo.domain.repository;

import com.papps.freddy_lazo.domain.model.Doctor;

import io.reactivex.Observable;

public interface UserRepository {

    Observable<Doctor> login(String email, String password);


}
