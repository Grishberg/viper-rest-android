package com.grishberg.viper_rest_android.presentation.injection;

import android.app.Service;
import android.content.Context;

import com.grishberg.viper_rest_android.data.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 09.06.16.
 */
@Module
public class ServiceModule {
    private static final String TAG = ServiceModule.class.getSimpleName();
    private final Service service;

    public ServiceModule(Service service) {
        this.service = service;
    }

    @Provides
    @Singleton
    public ApiService provideServiceGraph() {
        return ((ApiService) service).getObjectGraph();
    }

    @Provides
    @Singleton
    @ForService
    Context provideServiceContext() {
        return mService;
    }

    @Provides
    @Singleton
    Service provideService() {
        return mService;
    }
}
