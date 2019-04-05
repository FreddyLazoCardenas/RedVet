package com.papps.freddy_lazo.redvet.model;

public class ServiceDoctorModel {

    private int id;
    private int service_id;

    public ServiceDoctorModel(int id, int service_id) {
        this.id = id;
        this.service_id = service_id;
    }

    public int getId() {
        return id;
    }

    public int getService_id() {
        return service_id;
    }
}
