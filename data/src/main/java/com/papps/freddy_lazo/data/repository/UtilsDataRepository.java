package com.papps.freddy_lazo.data.repository;

import com.papps.freddy_lazo.data.entity.RedVetAppointmentEntity;
import com.papps.freddy_lazo.data.entity.mapper.AppointmentDoctorMapper;
import com.papps.freddy_lazo.data.entity.mapper.AppointmentPetLoverMapper;
import com.papps.freddy_lazo.data.entity.mapper.AppointmentPhotoMapper;
import com.papps.freddy_lazo.data.entity.mapper.NewsMapper;
import com.papps.freddy_lazo.data.entity.mapper.RedVetAppointmentMapper;
import com.papps.freddy_lazo.data.entity.mapper.RedVetMessageMapper;
import com.papps.freddy_lazo.data.entity.mapper.ServicesMapper;
import com.papps.freddy_lazo.data.network.RestApi;
import com.papps.freddy_lazo.domain.model.AppointmentPhoto;
import com.papps.freddy_lazo.domain.model.DoctorAppointment;
import com.papps.freddy_lazo.domain.model.PetLoverAppointment;
import com.papps.freddy_lazo.domain.model.News;
import com.papps.freddy_lazo.domain.model.RedVetAppointment;
import com.papps.freddy_lazo.domain.model.RedVetMessage;
import com.papps.freddy_lazo.domain.model.Service;
import com.papps.freddy_lazo.domain.repository.UtilsRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class UtilsDataRepository implements UtilsRepository {

    private final RestApi mRestApi;

    @Inject
    UtilsDataRepository(RestApi mRestApi) {
        this.mRestApi = mRestApi;
    }

    @Override
    public Observable<List<Service>> services() {
        return mRestApi.services().map(ServicesMapper::transform);
    }

    @Override
    public Observable<List<PetLoverAppointment>> petLoverAppointments(String apiToken) {
        return mRestApi.petLoverAppointment(apiToken).map(AppointmentPetLoverMapper::transform);
    }

    @Override
    public Observable<List<DoctorAppointment>> doctorAppointments(String apiToken) {
        return mRestApi.doctorAppointment(apiToken).map(AppointmentDoctorMapper::transform);
    }

    @Override
    public Observable<List<News>> petLoverNews(String path, String apiToken) {
        return mRestApi.petLoverNews(path, apiToken).map(NewsMapper::transform);
    }

    @Override
    public Observable<Void> forgotPassword(String email) {
        return mRestApi.forgotPassword(email);
    }

    @Override
    public Observable<RedVetAppointment> doctorConfirmAppointment(String apiToken, int appointmentId) {
        return mRestApi.doctorConfirmAppointment(apiToken, appointmentId).map(RedVetAppointmentMapper::transform);
    }

    @Override
    public Observable<RedVetAppointment> doctorFinishAppointment(String apiToken, int appointmentId, String diagnosis) {
        return mRestApi.doctorFinishAppointment(apiToken, appointmentId, diagnosis).map(RedVetAppointmentMapper::transform);
    }

    @Override
    public Observable<RedVetAppointment> doctorCancelAppointment(String apiToken, int appointmentId, String reason) {
        return mRestApi.doctorCancelAppointment(apiToken, appointmentId, reason).map(RedVetAppointmentMapper::transform);
    }

    @Override
    public Observable<RedVetAppointment> petLoverCancelAppointment(String apiToken, int appointmentId, String reason) {
        return mRestApi.petLoverCancelAppointment(apiToken, appointmentId, reason).map(RedVetAppointmentMapper::transform);
    }

    @Override
    public Observable<AppointmentPhoto> doctorUploadAppointmentPhoto(String apiToken, int appointmentId, String photo) {
        return mRestApi.doctorUploadAppointmentPhoto(apiToken, appointmentId, photo).map(AppointmentPhotoMapper::transform);
    }

    @Override
    public Observable<List<Void>> doctorDeleteAppointmentPhoto(String apiToken, int appointmentId, int appointment_photo_id) {
        return mRestApi.doctorDeleteAppointmentPhoto(apiToken, appointmentId, appointment_photo_id);
    }

    @Override
    public Observable<List<RedVetMessage>> petLoverChat(String auth, int appointmentId) {
        return mRestApi.petLoverChat(auth, appointmentId).map(RedVetMessageMapper::transform);
    }

    @Override
    public Observable<List<RedVetMessage>> doctorChat(String auth, int appointmentId) {
        return mRestApi.doctorChat(auth, appointmentId).map(RedVetMessageMapper::transform);
    }

    @Override
    public Observable<RedVetMessage> sendPetLoverMessage(String auth, int appointmentId, String message) {
        return mRestApi.sendPetLoverMessage(auth, appointmentId, message).map(RedVetMessageMapper::transform);
    }

    @Override
    public Observable<RedVetMessage> sendDoctorMessage(String auth, int appointmentId, String message) {
        return mRestApi.sendDoctorMessage(auth, appointmentId, message).map(RedVetMessageMapper::transform);
    }


}
