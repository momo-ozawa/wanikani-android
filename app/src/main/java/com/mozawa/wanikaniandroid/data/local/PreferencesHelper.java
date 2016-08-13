package com.mozawa.wanikaniandroid.data.local;


import android.content.Context;
import android.content.SharedPreferences;

import com.mozawa.wanikaniandroid.injection.ApplicationContext;

import javax.inject.Inject;

public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "wanikaniandroid_pref_file";

    private final SharedPreferences sharedPreferences;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
