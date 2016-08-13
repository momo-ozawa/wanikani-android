package com.mozawa.wanikaniandroid.injection.component;

import android.app.Application;
import android.content.Context;

import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.local.PreferencesHelper;
import com.mozawa.wanikaniandroid.data.remote.WaniKaniService;
import com.mozawa.wanikaniandroid.injection.ApplicationContext;
import com.mozawa.wanikaniandroid.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    WaniKaniService waniKaniService();

    PreferencesHelper preferencesHelper();

    DataManager dataManager();
}
