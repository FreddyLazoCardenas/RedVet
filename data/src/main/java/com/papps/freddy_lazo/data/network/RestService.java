package com.papps.freddy_lazo.data.network;

import com.papps.freddy_lazo.data.entity.ResponseEntity;
import com.papps.freddy_lazo.data.network.body.BodyAppointment;
import com.papps.freddy_lazo.data.network.body.BodyCancelAppointment;
import com.papps.freddy_lazo.data.network.body.BodyConfirmAppointment;
import com.papps.freddy_lazo.data.network.body.BodyDeletePet;
import com.papps.freddy_lazo.data.network.body.BodyDeletePhoto;
import com.papps.freddy_lazo.data.network.body.BodyDoctorRegister;
import com.papps.freddy_lazo.data.network.body.BodyFinishAppointment;
import com.papps.freddy_lazo.data.network.body.BodyLogin;
import com.papps.freddy_lazo.data.network.body.BodyPetLoverRegister;
import com.papps.freddy_lazo.data.network.body.BodyQualifyAppointment;
import com.papps.freddy_lazo.data.network.body.BodyRecoverPassword;
import com.papps.freddy_lazo.data.network.body.BodyRedVetChat;
import com.papps.freddy_lazo.data.network.body.BodyRedVetDeleteNotification;
import com.papps.freddy_lazo.data.network.body.BodyRedVetReadNotification;
import com.papps.freddy_lazo.data.network.body.BodySearchDoctors;
import com.papps.freddy_lazo.data.network.body.BodyUploadPhoto;
import com.papps.freddy_lazo.data.network.response.AppointmentPhotoResponse;
import com.papps.freddy_lazo.data.network.response.ChatRedVetResponse;
import com.papps.freddy_lazo.data.network.response.DoctorAppointmentResponse;
import com.papps.freddy_lazo.data.network.response.PetLoverAppointmentResponse;
import com.papps.freddy_lazo.data.network.response.CreateAppointmentResponse;
import com.papps.freddy_lazo.data.network.response.DoctorSearchResponse;
import com.papps.freddy_lazo.data.network.response.LoginResponse;
import com.papps.freddy_lazo.data.network.response.NewsResponse;
import com.papps.freddy_lazo.data.network.response.PetsRedVetResponse;
import com.papps.freddy_lazo.data.network.response.QualificationResponse;
import com.papps.freddy_lazo.data.network.response.RedVetAppointmentResponse;
import com.papps.freddy_lazo.data.network.response.RedVetNotificationsResponse;
import com.papps.freddy_lazo.data.network.response.RedVetReadNotificationsResponse;
import com.papps.freddy_lazo.data.network.response.ServicesResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RestService {

    @POST("login")
    Call<ResponseEntity<LoginResponse>> login(@Body BodyLogin bodyLogin);

    @POST("pet-lover/sign-up")
    Call<ResponseEntity<List<Void>>> petLoverRegister(@Body BodyPetLoverRegister bodyPetLoverRegister);

    @POST("doctor/sign-up")
    Call<ResponseEntity<List<Void>>> doctorRegister(@Body BodyDoctorRegister bodyDoctorRegister);

    @POST("pet-lover/search")
    Call<ResponseEntity<DoctorSearchResponse>> petLoverSearch(@Body BodySearchDoctors bodySearch, @Header("Authorization") String auth);

    @GET("{path}/news")
    Call<ResponseEntity<NewsResponse>> petLoverNews(@Header("Authorization") String auth, @Path("path") String path);

    @GET("services")
    Call<ResponseEntity<ServicesResponse>> services();

    @GET("pet-lover/appointments")
    Call<ResponseEntity<PetLoverAppointmentResponse>> petLoverAppointment(@Header("Authorization") String auth);

    @GET("doctor/appointments")
    Call<ResponseEntity<DoctorAppointmentResponse>> doctorAppointment(@Header("Authorization") String auth);

    @POST("forgot-password")
    Call<ResponseEntity<List<Void>>> forgotPassword(@Body BodyRecoverPassword bodyRecoverPassword);

    @POST("pet-lover/update")
    Call<ResponseEntity<LoginResponse>> updatePetLover(@Header("Authorization") String auth, @Body BodyPetLoverRegister bodyUpdatePetLover);

    @POST("doctor/update")
    Call<ResponseEntity<LoginResponse>> updateDoctor(@Header("Authorization") String auth, @Body BodyDoctorRegister bodyUpdatePetLover);

    @POST("pet-lover/appointments/create")
    Call<ResponseEntity<CreateAppointmentResponse>> createAppointment(@Header("Authorization") String auth, @Body BodyAppointment bodyUpdatePetLover);

    @POST("doctor/appointments/confirm")
    Call<ResponseEntity<DoctorAppointmentResponse>> doctorConfirmAppointment(@Header("Authorization") String auth, @Body BodyConfirmAppointment bodyConfirmAppointment);

    @POST("doctor/appointments/finish")
    Call<ResponseEntity<DoctorAppointmentResponse>> doctorFinishAppointment(@Header("Authorization") String auth, @Body BodyFinishAppointment bodyFinishAppointment);

    @POST("doctor/appointments/cancel")
    Call<ResponseEntity<DoctorAppointmentResponse>> doctorCancelAppointment(@Header("Authorization") String auth, @Body BodyCancelAppointment bodyFinishAppointment);

    @POST("pet-lover/appointments/cancel")
    Call<ResponseEntity<PetLoverAppointmentResponse>> petLoverCancelAppointment(@Header("Authorization") String auth, @Body BodyCancelAppointment bodyCancelAppointment);

    @Multipart
    @POST("doctor/appointments/upload-photo")
    Call<ResponseEntity<AppointmentPhotoResponse>> doctorUploadAppointmentPhoto(@Header("Authorization") String auth, @Part MultipartBody.Part photo, @Part("appointment_id") RequestBody appointmentId);

    @POST("doctor/appointments/delete-photo")
    Call<ResponseEntity<List<Void>>> doctorDeleteAppointmentPhoto(@Header("Authorization") String auth, @Body BodyDeletePhoto bodyDeletePhoto);

    @GET("pet-lover/appointments/chat/{appointment_id}")
    Call<ResponseEntity<ChatRedVetResponse>> petLoverChat(@Header("Authorization") String auth, @Path("appointment_id") int appointmentId);

    @GET("doctor/appointments/chat/{appointment_id}")
    Call<ResponseEntity<ChatRedVetResponse>> doctorChat(@Header("Authorization") String auth, @Path("appointment_id") int appointmentId);

    @POST("pet-lover/appointments/chat/send")
    Call<ResponseEntity<ChatRedVetResponse>> sendPetLoverMessage(@Header("Authorization") String auth, @Body BodyRedVetChat bodyRedVetChat);

    @POST("doctor/appointments/chat/send")
    Call<ResponseEntity<ChatRedVetResponse>> sendDoctorMessage(@Header("Authorization") String auth, @Body BodyRedVetChat bodyRedVetChat);

    @GET("pets")
    Call<ResponseEntity<PetsRedVetResponse>> getPets();

    @GET("appointments/{appointment_id}")
    Call<ResponseEntity<RedVetAppointmentResponse>> redVetAppointmentDetail(@Header("Authorization") String auth, @Path("appointment_id") int appointmentId);

    @POST("pet-lover/appointments/qualify")
    Call<ResponseEntity<QualificationResponse>> petLoverQualifyAppointment(@Header("Authorization") String auth, @Body BodyQualifyAppointment bodyQualifyAppointment);

    @POST("pet-lover/pet/destroy")
    Call<ResponseEntity<List<Void>>> deletePet(@Header("Authorization") String auth, @Body BodyDeletePet body);

    @GET("notifications")
    Call<ResponseEntity<RedVetNotificationsResponse>> redVetNotifications(@Header("Authorization") String auth);

    @POST("notifications/read")
    Call<ResponseEntity<RedVetReadNotificationsResponse>> redVetReadNotification(@Header("Authorization") String auth, @Body BodyRedVetReadNotification body);

    @POST("notifications/delete")
    Call<ResponseEntity<List<Void>>> deleteRedVetNotification(@Header("Authorization") String auth, @Body BodyRedVetDeleteNotification body);
}
