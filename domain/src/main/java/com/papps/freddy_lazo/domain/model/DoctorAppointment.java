package com.papps.freddy_lazo.domain.model;

import java.util.List;

public class DoctorAppointment {

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
    private Integer qualification;
    private String diagnosis;
    private PetLover petLover;
    private Pet pets;
    private List<AppointmentPhoto> photos;

    public DoctorAppointment(int id, String doctor_id, String pet_lover_id, String pet_by_pet_lover_id, String date, String time, String type, String reason, String status, String status_reason, Integer qualification, String diagnosis, PetLover petLover, Pet pets,List<AppointmentPhoto> photos ) {
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
        this.pets = pets;
        this.photos = photos;
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

    public Integer getQualification() {
        return qualification;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public PetLover getPetLover() {
        return petLover;
    }

    public Pet getPets() {
        return pets;
    }

    public List<AppointmentPhoto> getPhotos() {
        return photos;
    }
}
