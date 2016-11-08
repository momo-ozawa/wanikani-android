package com.mozawa.wanikaniandroid.injection.module;

import android.app.Application;
import android.content.Context;

import com.mozawa.wanikaniandroid.data.remote.WaniKaniService;
import com.mozawa.wanikaniandroid.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    protected final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    WaniKaniService provideWaniKaniService() {
        return WaniKaniService.Creator.newWaniKaniService();
    }
}
