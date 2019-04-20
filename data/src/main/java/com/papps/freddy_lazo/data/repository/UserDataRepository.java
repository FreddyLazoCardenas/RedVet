package com.papps.freddy_lazo.data.repository;

import com.papps.freddy_lazo.data.entity.mapper.AppointmentPetLoverMapper;
import com.papps.freddy_lazo.data.entity.mapper.CreateAppointmentMapper;
import com.papps.freddy_lazo.data.entity.mapper.DoctorLoginMapper;
import com.papps.freddy_lazo.data.entity.mapper.PetLoverLoginMapper;
import com.papps.freddy_lazo.data.entity.mapper.PetRedVetMapper;
import com.papps.freddy_lazo.data.entity.mapper.RedVetAppointmentDetailMapper;
import com.papps.freddy_lazo.data.entity.mapper.SearchDoctorsMapper;
import com.papps.freddy_lazo.data.network.RestApi;
import com.papps.freddy_lazo.domain.model.CreateAppointment;
import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.domain.model.PetLover;
import com.papps.freddy_lazo.domain.model.PetLoverAppointment;
import com.papps.freddy_lazo.domain.model.PetRedVet;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.model.RedVetDetailAppointment;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;
import com.papps.freddy_lazo.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class UserDataRepository implements UserRepository {

    private final RestApi mRestApi;

    @Inject
    UserDataRepository(RestApi mRestApi) {
        this.mRestApi = mRestApi;
    }

    @Override
    public Observable<Doctor> loginDoctor(String email, String password, String fcm_token) {
        return mRestApi.loginDoctor(email, password, fcm_token).map(DoctorLoginMapper::transform);
    }

    @Override
    public Observable<PetLover> loginPetLover(String email, String password, String fcm_token) {
        return mRestApi.loginPetLover(email, password, fcm_token).map(PetLoverLoginMapper::transform);
    }

    @Override
    public Observable<PetLover> updatePetLover(String apiToken, String email, String password, String firstName, String lastName, String dni, String address, String phone, String photo, String fcmToken, List<PetRegister> pets) {
        return mRestApi.updatePetLover(apiToken, email, password, firstName, lastName, dni, address, phone, photo, fcmToken, pets).map(PetLoverLoginMapper::transform);
    }

    @Override
    public Observable<Doctor> updateDoctor(String apiToken, String email, String password, String firstName, String lastName, String typeDocument, String numberDocument, String business_name, String address, String latitude, String longitude, String consultationPrice, String consultationTime, String shower_price, String shower_time, String tuition_number, String description, String phone, String photo, String type, String attention, String fcmToken, String device, List<PetRegister> pets, List<ScheduleDoctorRegister> schedules, List<ServicesDoctorRegister> services) {
        return mRestApi.updateDoctor(apiToken, email, password, firstName, lastName, typeDocument, numberDocument, business_name, address, latitude, longitude, consultationTime, consultationPrice, shower_time, shower_price, tuition_number, description, phone, photo, type, attention, fcmToken, device, pets, schedules, services).map(DoctorLoginMapper::transform);
    }

    @Override
    public Observable<List<Doctor>> searchDoctors(List<String> type, List<Integer> services, List<Integer> pets, String text, String apiToken) {
        return mRestApi.searchDoctors(type, services, pets, text, apiToken).map(SearchDoctorsMapper::transform);
    }

    @Override
    public Observable<List<Void>> signUpPetLover(String email, String password, String firstName, String lastName, String dni, String address, String phone, String photo, String fcmToken, List<PetRegister> pets) {
        return mRestApi.signUpPetLover(email, password, firstName, lastName, dni, address, phone, photo, fcmToken, pets);
    }

    @Override
    public Observable<List<Void>> doctorRegister(String email, String password, String firstName, String lastName, String typeDocument, String numberDocument, String business_name, String address, String latitude, String longitude, String consultationPrice, String consultationTime, String shower_price, String shower_time, String tuition_number, String description, String phone, String photo, String type, String attention, String fcmToken, String device, List<PetRegister> pets, List<ScheduleDoctorRegister> schedules, List<ServicesDoctorRegister> services) {
        return mRestApi.doctorRegister(email, password, firstName, lastName, typeDocument, numberDocument, business_name, address, latitude, longitude, consultationTime, consultationPrice, shower_time, shower_price, tuition_number, description, phone, photo, type, attention, fcmToken, device, pets, schedules, services);
    }

    @Override
    public Observable<CreateAppointment> createAppointment(String apiToken, int doctor_id, int pet_by_pet_lover_id, String date, String time, String type, String reason) {
        return mRestApi.createAppointment(apiToken, doctor_id, pet_by_pet_lover_id, date, time, type, reason).map(CreateAppointmentMapper::transform);
    }

    @Override
    public Observable<List<PetRedVet>> getPets() {
        return mRestApi.getPets().map(PetRedVetMapper::transform);
    }

    @Override
    public Observable<RedVetDetailAppointment> redVetAppointmentDetail(String auth, int appointmentId) {
        return mRestApi.redVetAppointmentDetail(auth, appointmentId).map(RedVetAppointmentDetailMapper::transform);
    }

    @Override
    public Observable<PetLoverAppointment> petLoverQualifyAppointment(String auth, int appointmentId, int qualification) {
        return mRestApi.petLoverQualifyAppointment(auth, appointmentId, qualification).map(AppointmentPetLoverMapper::transform);
    }
}
