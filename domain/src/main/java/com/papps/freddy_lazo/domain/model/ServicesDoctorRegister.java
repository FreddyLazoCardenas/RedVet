package com.papps.freddy_lazo.domain.model;

public class ServicesDoctorRegister {

    private int id;
    private int service_id;

    public ServicesDoctorRegister(int service_id) {
        this.service_id = service_id;
    }

    public ServicesDoctorRegister(int id, int service_id) {
        this.id = id;
        this.service_id = service_id;
    }
}
