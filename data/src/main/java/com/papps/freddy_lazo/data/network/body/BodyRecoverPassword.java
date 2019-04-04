package com.papps.freddy_lazo.data.network.body;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BodyRecoverPassword {

    @SerializedName("email")
    private String email;

    public BodyRecoverPassword(String email) {
        this.email = email;
    }

}
