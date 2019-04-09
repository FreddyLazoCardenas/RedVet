package com.papps.freddy_lazo.data.network;

import com.papps.freddy_lazo.data.entity.AppointmentPhotoEntity;
import com.papps.freddy_lazo.data.entity.DoctorAppointmentEntity;
import com.papps.freddy_lazo.data.entity.PetLoverAppointmentEntity;
import com.papps.freddy_lazo.data.entity.CreateAppointmentEntity;
import com.papps.freddy_lazo.data.entity.DoctorEntity;
import com.papps.freddy_lazo.data.entity.NewsEntity;
import com.papps.freddy_lazo.data.entity.PetLoverEntity;
import com.papps.freddy_lazo.data.entity.ServicesEntity;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public interface RestApi {

    Observable<DoctorEntity> loginDoctor(String email, String password);

    Observable<PetLoverEntity> loginPetLover(String email, String password);

    Observable<PetLoverEntity> updatePetLover(String apiToken, String email, String password, String firstName, String lastName, String dni, String address, String phone, String photo, String fcmToken, List<PetRegister> pets);

    Observable<DoctorEntity> updateDoctor(String apiToken, String email, String password, String firstName, String lastName, String typeDocument, String numberDocument, String business_name, String address, String latitude, String longitude, String consultationPrice, String consultationTime, String shower_price, String shower_time, String tuition_number, String description, String phone, String photo, String type, String attention, String fcmToken, String device, List<PetRegister> pets, List<ScheduleDoctorRegister> schedules, List<ServicesDoctorRegister> services);

    Observable<List<ServicesEntity>> services();

    Observable<List<PetLoverAppointmentEntity>> petLoverAppointment(String apiToken);

    Observable<List<DoctorAppointmentEntity>> doctorAppointment(String apiToken);

    Observable<Void> forgotPassword(String email);

    Observable<List<NewsEntity>> petLoverNews(String path);

    Observable<List<DoctorEntity>> searchDoctors(ArrayList<String> type, ArrayList<Integer> services, ArrayList<Integer> pets, String text);

    Observable<Void> signUpPetLover(String email, String password, String firstName, String lastName, String dni, String address, String phone, String photo, String fcmToken, List<PetRegister> pets);

    Observable<Void> doctorRegister(String email, String password, String firstName, String lastName, String typeDocument, String numberDocument, String business_name, String address, String latitude, String longitude, String consultationPrice, String consultationTime, String shower_price, String shower_time, String tuition_number, String description, String phone, String photo, String type, String attention, String fcmToken, String device, List<PetRegister> pets, List<ScheduleDoctorRegister> schedules, List<ServicesDoctorRegister> services);

    Observable<CreateAppointmentEntity> createAppointment(String apiToken, int doctor_id, int pet_by_pet_lover_id, String date, String time, String type, String reason);

    Observable<DoctorAppointmentEntity> doctorConfirmAppointment(String apiToken, int appointmentId);

    Observable<DoctorAppointmentEntity> doctorFinishAppointment(String apiToken, int appointmentId, String diagnosis);

    Observable<DoctorAppointmentEntity> doctorCancelAppointment(String apiToken, int appointmentId, String reason);

    Observable<PetLoverAppointmentEntity> petLoverCancelAppointment(String apiToken, int appointmentId);

    Observable<AppointmentPhotoEntity> doctorUploadAppointmentPhoto(String apiToken, int appointmentId, String photo);

    Observable<Void> doctorDeleteAppointmentPhoto(String apiToken, int appointmentId, int appointment_photo_id);
}
