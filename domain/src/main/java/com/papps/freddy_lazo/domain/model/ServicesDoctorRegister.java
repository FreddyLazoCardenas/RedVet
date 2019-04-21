package com.papps.freddy_lazo.domain.model;

public class ServicesDoctorRegister {

    private Integer id;
    private int service_id;

    public ServicesDoctorRegister(int service_id) {
        this.service_id = service_id;
    }

    public ServicesDoctorRegister(Integer id, int service_id) {
        this.id = id;
        this.service_id = service_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }
}
