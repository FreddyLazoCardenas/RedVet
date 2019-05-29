package com.papps.freddy_lazo.data.network;

import android.content.Context;


import com.papps.freddy_lazo.data.entity.AppointmentPhotoEntity;
import com.papps.freddy_lazo.data.entity.DoctorAppointmentEntity;
import com.papps.freddy_lazo.data.entity.PetLoverAppointmentEntity;
import com.papps.freddy_lazo.data.entity.CreateAppointmentEntity;
import com.papps.freddy_lazo.data.entity.DoctorEntity;
import com.papps.freddy_lazo.data.entity.NewsEntity;
import com.papps.freddy_lazo.data.entity.PetLoverEntity;
import com.papps.freddy_lazo.data.entity.PetRedVetEntity;
import com.papps.freddy_lazo.data.entity.RedVetAppointmentEntity;
import com.papps.freddy_lazo.data.entity.RedVetDetailAppointmentEntity;
import com.papps.freddy_lazo.data.entity.RedVetMessageEntity;
import com.papps.freddy_lazo.data.entity.RedVetNotificationEntity;
import com.papps.freddy_lazo.data.entity.ResponseEntity;
import com.papps.freddy_lazo.data.entity.ServicesEntity;
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
import com.papps.freddy_lazo.data.network.response.ServicesResponse;
import com.papps.freddy_lazo.domain.model.PetRegister;
import com.papps.freddy_lazo.domain.model.ScheduleDoctorRegister;
import com.papps.freddy_lazo.domain.model.ServicesDoctorRegister;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Response;

public class RestApiImpl implements RestApi {


    private final Context context;
    private final RestService restService;

    public RestApiImpl(Context context, RestService restService) {
        this.context = context;
        this.restService = restService;
    }

    @Override
    public Observable<DoctorEntity> loginDoctor(String email, String password, String fcm_token) {
        return Observable.create(emitter -> restService.login(new BodyLogin(email, password, fcm_token, "android")).enqueue(new DefaultCallback<ResponseEntity<LoginResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<LoginResponse>> call, @NonNull Response<ResponseEntity<LoginResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<LoginResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getDoctorEntity());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<PetLoverEntity> loginPetLover(String email, String password, String fcm_token) {
        return Observable.create(emitter -> restService.login(new BodyLogin(email, password, fcm_token, "android")).enqueue(new DefaultCallback<ResponseEntity<LoginResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<LoginResponse>> call, @NonNull Response<ResponseEntity<LoginResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<LoginResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getPetLoverEntity());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<PetLoverEntity> updatePetLover(String apiToken, String email, String password, String firstName, String lastName, String dni, String address, String phone, String photo, String fcmToken, List<PetRegister> pets) {
        return Observable.create(emitter -> restService.updatePetLover("Bearer " + apiToken, new BodyPetLoverRegister(email, password, firstName, lastName, dni, address, phone, photo, fcmToken, "android", pets)).enqueue(new DefaultCallback<ResponseEntity<LoginResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<LoginResponse>> call, @NonNull Response<ResponseEntity<LoginResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<LoginResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getPetLoverEntity());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<DoctorEntity> updateDoctor(String apiToken, String email, String password, String firstName, String lastName, String typeDocument, String numberDocument, String business_name, String address, String latitude, String longitude, String consultationPrice, String consultationTime, String shower_price, String shower_time, String tuition_number, String description, String phone, String photo, String type, String attention, String fcmToken, String device, List<PetRegister> pets, List<ScheduleDoctorRegister> schedules, List<ServicesDoctorRegister> services) {
        return Observable.create(emitter -> restService.updateDoctor("Bearer " + apiToken, new BodyDoctorRegister(email, password, firstName, lastName, typeDocument, numberDocument, business_name, address, latitude, longitude, consultationPrice, consultationTime, shower_price, shower_time, tuition_number, description, phone, photo, type, attention, fcmToken, device, pets, schedules, services)).enqueue(new DefaultCallback<ResponseEntity<LoginResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<LoginResponse>> call, @NonNull Response<ResponseEntity<LoginResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<LoginResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getDoctorEntity());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<ServicesEntity>> services() {
        return Observable.create(emitter -> restService.services().enqueue(new DefaultCallback<ResponseEntity<ServicesResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<ServicesResponse>> call, @NonNull Response<ResponseEntity<ServicesResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<ServicesResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getServicesEntityList());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<PetLoverAppointmentEntity>> petLoverAppointment(String apiToken) {
        return Observable.create(emitter -> restService.petLoverAppointment("Bearer " + apiToken).enqueue(new DefaultCallback<ResponseEntity<PetLoverAppointmentResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<PetLoverAppointmentResponse>> call, @NonNull Response<ResponseEntity<PetLoverAppointmentResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<PetLoverAppointmentResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getAppointmentList());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<DoctorAppointmentEntity>> doctorAppointment(String apiToken) {
        return Observable.create(emitter -> restService.doctorAppointment("Bearer " + apiToken).enqueue(new DefaultCallback<ResponseEntity<DoctorAppointmentResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<DoctorAppointmentResponse>> call, @NonNull Response<ResponseEntity<DoctorAppointmentResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<DoctorAppointmentResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getAppointmentList());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<Void>> forgotPassword(String email) {
        return Observable.create(emitter -> restService.forgotPassword(new BodyRecoverPassword(email)).enqueue(new DefaultCallback<ResponseEntity<List<Void>>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<List<Void>>> call, @NonNull Response<ResponseEntity<List<Void>>> response) {
                super.onResponse(call, response);
                ResponseEntity<List<Void>> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<NewsEntity>> petLoverNews(String path, String apiToken) {
        return Observable.create(emitter -> restService.petLoverNews("Bearer " + apiToken, path).enqueue(new DefaultCallback<ResponseEntity<NewsResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<NewsResponse>> call, @NonNull Response<ResponseEntity<NewsResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<NewsResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getNewsEntityList());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<DoctorEntity>> searchDoctors(List<String> type, List<Integer> services, List<Integer> pets, String text, String apiToken) {
        return Observable.create(emitter -> restService.petLoverSearch(new BodySearchDoctors(type, services, pets, text), "Bearer " + apiToken).enqueue(new DefaultCallback<ResponseEntity<DoctorSearchResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<DoctorSearchResponse>> call, @NonNull Response<ResponseEntity<DoctorSearchResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<DoctorSearchResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getDoctorEntity());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<Void>> signUpPetLover(String email, String password, String firstName, String lastName, String dni, String address, String phone, String photo, String fcmToken, List<PetRegister> pets) {
        return Observable.create(emitter -> restService.petLoverRegister(new BodyPetLoverRegister(email, password, firstName, lastName, dni, address, phone, photo, fcmToken, "android", pets)).enqueue(new DefaultCallback<ResponseEntity<List<Void>>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<List<Void>>> call, @NonNull Response<ResponseEntity<List<Void>>> response) {
                super.onResponse(call, response);
                ResponseEntity<List<Void>> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<Void>> doctorRegister(String email, String password, String firstName, String lastName, String typeDocument, String numberDocument, String business_name, String address, String latitude, String longitude, String consultationPrice, String consultationTime, String shower_price, String shower_time, String tuition_number, String description, String phone, String photo, String type, String attention, String fcmToken, String device, List<PetRegister> pets, List<ScheduleDoctorRegister> schedules, List<ServicesDoctorRegister> services) {
        return Observable.create(emitter -> restService.doctorRegister(new BodyDoctorRegister(email, password, firstName, lastName, typeDocument, numberDocument, business_name, address, latitude, longitude, consultationPrice, consultationTime, shower_price, shower_time, tuition_number, description, phone, photo, type, attention, fcmToken, device, pets, schedules, services)).enqueue(new DefaultCallback<ResponseEntity<List<Void>>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<List<Void>>> call, @NonNull Response<ResponseEntity<List<Void>>> response) {
                super.onResponse(call, response);
                ResponseEntity<List<Void>> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<CreateAppointmentEntity> createAppointment(String apiToken, int doctor_id, int pet_by_pet_lover_id, String date, String time, String type, String reason) {
        return Observable.create(emitter -> restService.createAppointment("Bearer " + apiToken, new BodyAppointment(doctor_id, pet_by_pet_lover_id, date, time, type, reason)).enqueue(new DefaultCallback<ResponseEntity<CreateAppointmentResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<CreateAppointmentResponse>> call, @NonNull Response<ResponseEntity<CreateAppointmentResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<CreateAppointmentResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getAppointment());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<RedVetAppointmentEntity> doctorConfirmAppointment(String apiToken, int appointmentId) {
        return Observable.create(emitter -> restService.doctorConfirmAppointment("Bearer " + apiToken, new BodyConfirmAppointment(appointmentId)).enqueue(new DefaultCallback<ResponseEntity<DoctorAppointmentResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<DoctorAppointmentResponse>> call, @NonNull Response<ResponseEntity<DoctorAppointmentResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<DoctorAppointmentResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getAppointment());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<RedVetAppointmentEntity> doctorFinishAppointment(String apiToken, int appointmentId, String diagnosis) {
        return Observable.create(emitter -> restService.doctorFinishAppointment("Bearer " + apiToken, new BodyFinishAppointment(appointmentId, diagnosis)).enqueue(new DefaultCallback<ResponseEntity<DoctorAppointmentResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<DoctorAppointmentResponse>> call, @NonNull Response<ResponseEntity<DoctorAppointmentResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<DoctorAppointmentResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getAppointment());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<RedVetAppointmentEntity> doctorCancelAppointment(String apiToken, int appointmentId, String reason) {
        return Observable.create(emitter -> restService.doctorCancelAppointment("Bearer " + apiToken, new BodyCancelAppointment(appointmentId, reason)).enqueue(new DefaultCallback<ResponseEntity<DoctorAppointmentResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<DoctorAppointmentResponse>> call, @NonNull Response<ResponseEntity<DoctorAppointmentResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<DoctorAppointmentResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getAppointment());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<RedVetAppointmentEntity> petLoverCancelAppointment(String apiToken, int appointmentId, String reason) {
        return Observable.create(emitter -> restService.petLoverCancelAppointment("Bearer " + apiToken, new BodyCancelAppointment(appointmentId, reason)).enqueue(new DefaultCallback<ResponseEntity<PetLoverAppointmentResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<PetLoverAppointmentResponse>> call, @NonNull Response<ResponseEntity<PetLoverAppointmentResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<PetLoverAppointmentResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getAppointment());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<AppointmentPhotoEntity> doctorUploadAppointmentPhoto(String apiToken, int appointmentId, String photo) {
        return Observable.create(emitter -> restService.doctorUploadAppointmentPhoto("Bearer " + apiToken, new BodyUploadPhoto(appointmentId, photo)).enqueue(new DefaultCallback<ResponseEntity<AppointmentPhotoResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<AppointmentPhotoResponse>> call, @NonNull Response<ResponseEntity<AppointmentPhotoResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<AppointmentPhotoResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getAppointment_photo());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<Void>> doctorDeleteAppointmentPhoto(String apiToken, int appointmentId, int appointment_photo_id) {
        return Observable.create(emitter -> restService.doctorDeleteAppointmentPhoto("Bearer " + apiToken, new BodyDeletePhoto(appointmentId, appointment_photo_id)).enqueue(new DefaultCallback<ResponseEntity<List<Void>>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<List<Void>>> call, @NonNull Response<ResponseEntity<List<Void>>> response) {
                super.onResponse(call, response);
                ResponseEntity<List<Void>> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<RedVetMessageEntity>> petLoverChat(String auth, int appointmentId) {
        return Observable.create(emitter -> restService.petLoverChat("Bearer " + auth, appointmentId).enqueue(new DefaultCallback<ResponseEntity<ChatRedVetResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<ChatRedVetResponse>> call, @NonNull Response<ResponseEntity<ChatRedVetResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<ChatRedVetResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getMessages());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<RedVetMessageEntity>> doctorChat(String auth, int appointmentId) {
        return Observable.create(emitter -> restService.doctorChat("Bearer " + auth, appointmentId).enqueue(new DefaultCallback<ResponseEntity<ChatRedVetResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<ChatRedVetResponse>> call, @NonNull Response<ResponseEntity<ChatRedVetResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<ChatRedVetResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getMessages());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<RedVetMessageEntity> sendPetLoverMessage(String auth, int appointmentId, String message) {
        return Observable.create(emitter -> restService.sendPetLoverMessage("Bearer " + auth, new BodyRedVetChat(appointmentId, message)).enqueue(new DefaultCallback<ResponseEntity<ChatRedVetResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<ChatRedVetResponse>> call, @NonNull Response<ResponseEntity<ChatRedVetResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<ChatRedVetResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getMessage());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<RedVetMessageEntity> sendDoctorMessage(String auth, int appointmentId, String message) {
        return Observable.create(emitter -> restService.sendDoctorMessage("Bearer " + auth, new BodyRedVetChat(appointmentId, message)).enqueue(new DefaultCallback<ResponseEntity<ChatRedVetResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<ChatRedVetResponse>> call, @NonNull Response<ResponseEntity<ChatRedVetResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<ChatRedVetResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getMessage());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<PetRedVetEntity>> getPets() {
        return Observable.create(emitter -> restService.getPets().enqueue(new DefaultCallback<ResponseEntity<PetsRedVetResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<PetsRedVetResponse>> call, @NonNull Response<ResponseEntity<PetsRedVetResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<PetsRedVetResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getPets());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<RedVetDetailAppointmentEntity> redVetAppointmentDetail(String auth, int appointmentId) {
        return Observable.create(emitter -> restService.redVetAppointmentDetail("Bearer " + auth, appointmentId).enqueue(new DefaultCallback<ResponseEntity<RedVetAppointmentResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<RedVetAppointmentResponse>> call, @NonNull Response<ResponseEntity<RedVetAppointmentResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<RedVetAppointmentResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getAppointment());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<PetLoverAppointmentEntity> petLoverQualifyAppointment(String auth, int appointmentId, int qualification) {
        return Observable.create(emitter -> restService.petLoverQualifyAppointment("Bearer " + auth, new BodyQualifyAppointment(appointmentId, qualification)).enqueue(new DefaultCallback<ResponseEntity<QualificationResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<QualificationResponse>> call, @NonNull Response<ResponseEntity<QualificationResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<QualificationResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getAppointment());
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<Void>> deletePet(String auth, int petLoverId) {
        return Observable.create(emitter -> restService.deletePet("Bearer " + auth, new BodyDeletePet(petLoverId)).enqueue(new DefaultCallback<ResponseEntity<List<Void>>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<List<Void>>> call, @NonNull Response<ResponseEntity<List<Void>>> response) {
                super.onResponse(call, response);
                ResponseEntity<List<Void>> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<RedVetNotificationEntity>> redVetNotifications(String auth) {
        return Observable.create(emitter -> restService.redVetNotifications("Bearer " + auth).enqueue(new DefaultCallback<ResponseEntity<RedVetNotificationsResponse>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<RedVetNotificationsResponse>> call, @NonNull Response<ResponseEntity<RedVetNotificationsResponse>> response) {
                super.onResponse(call, response);
                ResponseEntity<RedVetNotificationsResponse> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onNext(body.getData().getNotifications());
                    emitter.onComplete();
                }
            }
        }));
    }

}
