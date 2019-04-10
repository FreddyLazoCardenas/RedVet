package com.papps.freddy_lazo.redvet.model;

import com.google.gson.Gson;
import com.papps.freddy_lazo.domain.model.Pet;
import com.papps.freddy_lazo.domain.model.PetLover;

public class DoctorAppointmentModel {

    private int id;
    private String doctor_id;
    private String pet_lover_id;
    private String pet_by_pet_lover_id;
    private String date;
    private String time;
    private String type;
    private String reason;
    private String status;
    private String status_reason;
    private double qualification;
    private String diagnosis;
    private PetLoverModel petLover;
    private PetLoverRegisterModel pet;

    public DoctorAppointmentModel(int id, String doctor_id, String pet_lover_id, String pet_by_pet_lover_id, String date, String time, String type, String reason, String status, String status_reason
            , double qualification, String diagnosis, PetLoverModel petLover ,PetLoverRegisterModel pet ) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.pet_lover_id = pet_lover_id;
        this.pet_by_pet_lover_id = pet_by_pet_lover_id;
        this.date = date;
        this.time = time;
        this.type = type;
        this.reason = reason;
        this.status = status;
        this.status_reason = status_reason;
        this.qualification = qualification;
        this.diagnosis = diagnosis;
        this.petLover = petLover;
        this.pet = pet;
    }

    public int getId() {
        return id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public String getPet_lover_id() {
        return pet_lover_id;
    }

    public String getPet_by_pet_lover_id() {
        return pet_by_pet_lover_id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public String getStatus_reason() {
        return status_reason;
    }

    public double getQualification() {
        return qualification;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public PetLoverModel getPetLover() {
        return petLover;
    }

    public PetLoverRegisterModel getPet() {
        return pet;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, DoctorAppointmentModel.class);
    }

    public static DoctorAppointmentModel toModel(String srtUser) {
        return srtUser != null && !srtUser.isEmpty() ? new Gson().fromJson(srtUser, DoctorAppointmentModel.class) : null;
    }

}
