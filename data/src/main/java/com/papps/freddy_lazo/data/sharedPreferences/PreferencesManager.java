package com.papps.freddy_lazo.data.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.content.Context.MODE_PRIVATE;

@Singleton
public class PreferencesManager {

    private static final String KEY_CURRENT_USER = "key_current_user";
    private static final String NAME_CURRENT_USER = "key_current_user";
    private final Context context;


    @Inject
    public PreferencesManager(Context context) {
        this.context = context;
    }

    public void saveCurrentUser(String currentUser) {
        SharedPreferences.Editor editor = context.getSharedPreferences(NAME_CURRENT_USER, MODE_PRIVATE).edit();
        editor.putString(KEY_CURRENT_USER, currentUser);
        editor.apply();
    }

    public String getCurrentUser() {
        SharedPreferences prefs = context.getSharedPreferences(NAME_CURRENT_USER, MODE_PRIVATE);
        return prefs.getString(KEY_CURRENT_USER, null);
    }

}
