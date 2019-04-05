package com.papps.freddy_lazo.domain.model;


public class ServiceDoctor {

    private int id;
    private int service_id;

    public ServiceDoctor(int id, int service_id) {
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
