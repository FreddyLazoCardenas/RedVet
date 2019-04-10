package com.papps.freddy_lazo.data.network;

import android.content.Context;


import com.papps.freddy_lazo.data.entity.AppointmentPhotoEntity;
import com.papps.freddy_lazo.data.entity.DoctorAppointmentEntity;
import com.papps.freddy_lazo.data.entity.PetLoverAppointmentEntity;
import com.papps.freddy_lazo.data.entity.CreateAppointmentEntity;
import com.papps.freddy_lazo.data.entity.DoctorEntity;
import com.papps.freddy_lazo.data.entity.NewsEntity;
import com.papps.freddy_lazo.data.entity.PetLoverEntity;
import com.papps.freddy_lazo.data.entity.ResponseEntity;
import com.papps.freddy_lazo.data.entity.ServicesEntity;
import com.papps.freddy_lazo.data.network.body.BodyAppointment;
import com.papps.freddy_lazo.data.network.body.BodyCancelAppointment;
import com.papps.freddy_lazo.data.network.body.BodyConfirmAppointment;
import com.papps.freddy_lazo.data.network.body.BodyDeletePhoto;
import com.papps.freddy_lazo.data.network.body.BodyDoctorRegister;
import com.papps.freddy_lazo.data.network.body.BodyFinishAppointment;
import com.papps.freddy_lazo.data.network.body.BodyLogin;
import com.papps.freddy_lazo.data.network.body.BodyPetLoverRegister;
import com.papps.freddy_lazo.data.network.body.BodyRecoverPassword;
import com.papps.freddy_lazo.data.network.body.BodySearchDoctors;
import com.papps.freddy_lazo.data.network.body.BodyUploadPhoto;
import com.papps.freddy_lazo.data.network.response.AppointmentPhotoResponse;
import com.papps.freddy_lazo.data.network.response.DoctorAppointmentResponse;
import com.papps.freddy_lazo.data.network.response.PetLoverAppointmentResponse;
import com.papps.freddy_lazo.data.network.response.CreateAppointmentResponse;
import com.papps.freddy_lazo.data.network.response.DoctorSearchResponse;
import com.papps.freddy_lazo.data.network.response.LoginResponse;
import com.papps.freddy_lazo.data.network.response.NewsResponse;
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
    public Observable<DoctorEntity> loginDoctor(String email, String password) {
        return Observable.create(emitter -> restService.login(new BodyLogin(email, password, "asdd", "android")).enqueue(new DefaultCallback<ResponseEntity<LoginResponse>>(emitter) {
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
    public Observable<PetLoverEntity> loginPetLover(String email, String password) {
        return Observable.create(emitter -> restService.login(new BodyLogin(email, password, "asdd", "android")).enqueue(new DefaultCallback<ResponseEntity<LoginResponse>>(emitter) {
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
    public Observable<Void> forgotPassword(String email) {
        return Observable.create(emitter -> restService.forgotPassword(new BodyRecoverPassword(email)).enqueue(new DefaultCallback<ResponseEntity<Void>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<Void>> call, @NonNull Response<ResponseEntity<Void>> response) {
                super.onResponse(call, response);
                ResponseEntity<Void> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<List<NewsEntity>> petLoverNews(String path) {
        return Observable.create(emitter -> restService.petLoverNews("Bearer wuGnaJzXVEFFJkfo", path).enqueue(new DefaultCallback<ResponseEntity<NewsResponse>>(emitter) {
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
    public Observable<List<DoctorEntity>> searchDoctors(ArrayList<String> type, ArrayList<Integer> services, ArrayList<Integer> pets, String text) {
        return Observable.create(emitter -> restService.petLoverSearch(new BodySearchDoctors(type, services, pets, text), "Bearer wuGnaJzXVEFFJkfo").enqueue(new DefaultCallback<ResponseEntity<DoctorSearchResponse>>(emitter) {
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
    public Observable<Void> signUpPetLover(String email, String password, String firstName, String lastName, String dni, String address, String phone, String photo, String fcmToken, List<PetRegister> pets) {
        return Observable.create(emitter -> restService.petLoverRegister(new BodyPetLoverRegister(email, password, firstName, lastName, dni, address, phone, photo, fcmToken, "android", pets)).enqueue(new DefaultCallback<ResponseEntity<Void>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<Void>> call, @NonNull Response<ResponseEntity<Void>> response) {
                super.onResponse(call, response);
                ResponseEntity<Void> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onComplete();
                }
            }
        }));
    }

    @Override
    public Observable<Void> doctorRegister(String email, String password, String firstName, String lastName, String typeDocument, String numberDocument, String business_name, String address, String latitude, String longitude, String consultationPrice, String consultationTime, String shower_price, String shower_time, String tuition_number, String description, String phone, String photo, String type, String attention, String fcmToken, String device, List<PetRegister> pets, List<ScheduleDoctorRegister> schedules, List<ServicesDoctorRegister> services) {
        return Observable.create(emitter -> restService.doctorRegister(new BodyDoctorRegister(email, password, firstName, lastName, typeDocument, numberDocument, business_name, address, latitude, longitude, consultationPrice, consultationTime, shower_price, shower_time, tuition_number, description, phone, photo, type, attention, fcmToken, device, pets, schedules, services)).enqueue(new DefaultCallback<ResponseEntity<Void>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<Void>> call, @NonNull Response<ResponseEntity<Void>> response) {
                super.onResponse(call, response);
                ResponseEntity<Void> body = response.body();
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
    public Observable<DoctorAppointmentEntity> doctorConfirmAppointment(String apiToken, int appointmentId) {
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
    public Observable<DoctorAppointmentEntity> doctorFinishAppointment(String apiToken, int appointmentId, String diagnosis) {
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
    public Observable<DoctorAppointmentEntity> doctorCancelAppointment(String apiToken, int appointmentId, String reason) {
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
    public Observable<PetLoverAppointmentEntity> petLoverCancelAppointment(String apiToken, int appointmentId) {
        return Observable.create(emitter -> restService.petLoverCancelAppointment("Bearer " + apiToken, new BodyCancelAppointment(appointmentId)).enqueue(new DefaultCallback<ResponseEntity<PetLoverAppointmentResponse>>(emitter) {
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
    public Observable<Void> doctorDeleteAppointmentPhoto(String apiToken, int appointmentId, int appointment_photo_id) {
        return Observable.create(emitter -> restService.doctorDeleteAppointmentPhoto("Bearer " + apiToken, new BodyDeletePhoto(appointmentId, appointment_photo_id)).enqueue(new DefaultCallback<ResponseEntity<Void>>(emitter) {
            @Override
            public void onResponse(@NonNull Call<ResponseEntity<Void>> call, @NonNull Response<ResponseEntity<Void>> response) {
                super.onResponse(call, response);
                ResponseEntity<Void> body = response.body();
                if (body != null && body.getMessage() == null) {
                    emitter.onComplete();
                }
            }
        }));
    }

}
