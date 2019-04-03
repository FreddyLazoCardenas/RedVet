package com.papps.freddy_lazo.domain.repository;

import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.domain.model.PetRegister;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public interface UserRepository {

    Observable<Doctor> login(String email, String password);

    Observable<List<Doctor>> searchDoctors(ArrayList<String> type, ArrayList<Integer> services, ArrayList<Integer> pets, String text);

    Observable<Void> signUpPetLover(String email, String password, String firstName, String lastName, String dni, String address, String phone, String fcmToken, List<PetRegister> pets);

}
