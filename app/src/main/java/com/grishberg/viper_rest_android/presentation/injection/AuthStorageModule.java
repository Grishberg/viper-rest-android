package com.grishberg.viper_rest_android.presentation.injection;

import com.grishberg.viper_rest_android.domain.interfaces.AuthStorageService;
import com.grishberg.viper_rest_android.presentation.main.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 13.06.16.
 */
@Singleton
@Module
public class AuthStorageModule {
    private static final String TAG = AuthStorageModule.class.getSimpleName();

    private final App application;

    public AuthStorageModule(App app) {
        this.application = app;
    }

    @Provides
    @Singleton
        // Application reference must come from AppModule.class
    AuthStorageService providesAuthStorageService() {
        return application;
    }
}
