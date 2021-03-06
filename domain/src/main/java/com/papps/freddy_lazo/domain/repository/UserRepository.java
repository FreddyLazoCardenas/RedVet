package com.papps.freddy_lazo.domain.repository;

import com.papps.freddy_lazo.domain.model.CreateAppointment;
import com.papps.freddy_lazo.domain.model.Doctor;
import com.papps.freddy_lazo.domain.model.PetLover;
import com.papps.freddy_lazo.domain.model.PetLoverAppointment;
import com.papps.freddy_lazo.domain.model.PetRedVet;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.model.RedVetDetailAppointment;
import com.papps.freddy_lazo.domain.model.RedVetNotification;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public interface UserRepository {

    Observable<Doctor> loginDoctor(String email, String password, String fcm_token);

    Observable<PetLover> loginPetLover(String email, String password, String fcm_token);

    Observable<PetLover> updatePetLover(String apiToken, String email, String password, String firstName, String lastName, String dni, String address, String phone, String photo, String fcmToken, List<PetRegister> pets);

    Observable<Doctor> updateDoctor(String apiToken, String email, String password, String firstName, String lastName, String typeDocument, String numberDocument, String business_name, String address, String latitude, String longitude, String consultationPrice, String consultationTime, String shower_price, String shower_time, String tuition_number, String description, String phone, String photo, String type, String attention, String fcmToken, String device, List<PetRegister> pets, List<ScheduleDoctorRegister> schedules, List<ServicesDoctorRegister> services);

    Observable<List<Doctor>> searchDoctors(List<String> type, List<Integer> services, List<Integer> pets, String text, String apiToken);

    Observable<List<Void>> signUpPetLover(String email, String password, String firstName, String lastName, String dni, String address, String phone, String photo, String fcmToken, List<PetRegister> pets);

    Observable<List<Void>> doctorRegister(String email, String password, String firstName, String lastName, String typeDocument, String numberDocument, String business_name, String address, String latitude, String longitude, String consultationPrice, String consultationTime, String shower_price, String shower_time, String tuition_number, String description, String phone, String photo, String type, String attention, String fcmToken, String device, List<PetRegister> pets, List<ScheduleDoctorRegister> schedules, List<ServicesDoctorRegister> services);

    Observable<CreateAppointment> createAppointment(String apiToken, int doctor_id, int pet_by_pet_lover_id, String date, String time, String type, String reason);

    Observable<List<PetRedVet>> getPets();

    Observable<RedVetDetailAppointment> redVetAppointmentDetail(String auth, int appointmentId);

    Observable<PetLoverAppointment> petLoverQualifyAppointment(String auth, int appointmentId, int qualification);

    Observable<List<Void>> deletePet(String auth, int petLoverId);

    Observable<List<RedVetNotification>> redVetNotifications(String auth);

    Observable<RedVetNotification> redVetReadNotification(String auth , int notId);

    Observable<List<Void>> deleteRedVetNotification(String auth, int petLoverId);
}
