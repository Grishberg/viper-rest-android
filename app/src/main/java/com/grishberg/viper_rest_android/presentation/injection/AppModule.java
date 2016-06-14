package com.grishberg.viper_rest_android.presentation.injection;

import android.app.Application;
import android.content.Context;

import com.grishberg.viper_rest_android.data.RxApiService;
import com.grishberg.viper_rest_android.data.RxOrderServiceImpl;
import com.grishberg.viper_rest_android.domain.interfaces.AuthStorageService;
import com.grishberg.viper_rest_android.presentation.main.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 11.06.16.
 */
@Module
public class AppModule {
    private final App application;

    public AppModule(App app) {
        this.application = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    RxApiService provideApiService() {
        //application.getApplicationContext()
        return new RxOrderServiceImpl();
    }
}