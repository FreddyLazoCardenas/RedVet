package com.papps.freddy_lazo.data.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.content.Context.MODE_PRIVATE;

@Singleton
public class PreferencesManager {

    private static final String KEY_DOCTOR_CURRENT_USER = "key_doctor_current_user";
    private static final String KEY_PET_LOVER_CURRENT_USER = "key_pet_lover_current_user";
    private static final String NAME_CURRENT_USER = "key_current_user";
    private final Context context;


    @Inject
    public PreferencesManager(Context context) {
        this.context = context;
    }

    public void saveDoctorCurrentUser(String currentUser) {
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME_CURRENT_USER, MODE_PRIVATE).edit();
        editor.putString(KEY_DOCTOR_CURRENT_USER, currentUser);
        editor.apply();
    }

    public void savePetLoverCurrentUser(String currentUser) {
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME_CURRENT_USER, MODE_PRIVATE).edit();
        editor.putString(KEY_PET_LOVER_CURRENT_USER, currentUser);
        editor.apply();
    }

    public String getDoctorCurrentUser() {
        SharedPreferences prefs = context.getSharedPreferences(NAME_CURRENT_USER, MODE_PRIVATE);
        return prefs.getString(KEY_DOCTOR_CURRENT_USER, null);
    }

    public String getPetLoverCurrentUser() {
        SharedPreferences prefs = context.getSharedPreferences(NAME_CURRENT_USER, MODE_PRIVATE);
        return prefs.getString(KEY_PET_LOVER_CURRENT_USER, null);
    }

}
