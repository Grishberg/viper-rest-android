package com.grishberg.viper_rest_android.presentation.injection;

import android.content.Context;

import com.grishberg.viper_rest_android.data.RxApiService;
import com.grishberg.viper_rest_android.data.RxOrderServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 11.06.16.
 */
@Module
public class ApiServiceModule {
    private static final String TAG = ApiServiceModule.class.getSimpleName();

    private final Context context;

    public ApiServiceModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    RxApiService provideContext() {
        return new RxOrderServiceImpl();
    }
}
