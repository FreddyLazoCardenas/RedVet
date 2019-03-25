package com.papps.freddy_lazo.data.entity;
import com.google.gson.annotations.SerializedName;

public class ResponseEntity<T> {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private T data;

    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

