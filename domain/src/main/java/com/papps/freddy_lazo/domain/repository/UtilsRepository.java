package com.papps.freddy_lazo.domain.repository;

import com.papps.freddy_lazo.domain.model.AppointmentPhoto;
import com.papps.freddy_lazo.domain.model.DoctorAppointment;
import com.papps.freddy_lazo.domain.model.Notification;
import com.papps.freddy_lazo.domain.model.PetLoverAppointment;
import com.papps.freddy_lazo.domain.model.News;
import com.papps.freddy_lazo.domain.model.RedVetAppointment;
import com.papps.freddy_lazo.domain.model.RedVetMessage;
import com.papps.freddy_lazo.domain.model.Service;

import java.util.List;

import io.reactivex.Observable;

public interface UtilsRepository {

    Observable<List<Service>> services();

    Observable<List<PetLoverAppointment>> petLoverAppointments(String apiToken);

    Observable<List<DoctorAppointment>> doctorAppointments(String apiToken);

    Observable<List<Void>> forgotPassword(String email);

    Observable<List<News>> petLoverNews(String query, String apiToken);

    Observable<RedVetAppointment> doctorConfirmAppointment(String apiToken, int appointmentId);

    Observable<RedVetAppointment> doctorFinishAppointment(String apiToken, int appointmentId, String diagnosis);

    Observable<RedVetAppointment> doctorCancelAppointment(String apiToken, int appointmentId, String reason);

    Observable<RedVetAppointment> petLoverCancelAppointment(String apiToken, int appointmentId, String reason);

    Observable<AppointmentPhoto> doctorUploadAppointmentPhoto(String apiToken, int appointmentId, byte[] photo, String parseType);

    Observable<List<Void>> doctorDeleteAppointmentPhoto(String apiToken, int appointmentId, int appointment_photo_id);

    Observable<List<RedVetMessage>> petLoverChat(String auth, int appointmentId);

    Observable<List<RedVetMessage>> doctorChat(String auth, int appointmentId);

    Observable<RedVetMessage> sendPetLoverMessage(String auth, int appointmentId, String message);

    Observable<RedVetMessage> sendDoctorMessage(String auth, int appointmentId, String message);

    Observable<List<Notification>> listNotifications();

    Observable<Void> saveNotification(Notification notification);

    Observable<Void> deleteSpecificNotification(Integer id);


}
