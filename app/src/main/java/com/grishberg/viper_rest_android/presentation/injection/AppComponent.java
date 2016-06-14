package com.grishberg.viper_rest_android.presentation.injection;


import android.content.Context;

import com.grishberg.viper_rest_android.data.ApiService;
import com.grishberg.viper_rest_android.data.RxOrderServiceImpl;
import com.grishberg.viper_rest_android.data.providers.AuthDataProviderImpl;
import com.grishberg.viper_rest_android.data.providers.RegisterDataProviderImpl;
import com.grishberg.viper_rest_android.data.providers.ShopsDataProviderImpl;
import com.grishberg.viper_rest_android.presentation.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grishberg on 11.06.16.
 */
@Singleton
@Component(modules = {RestModule.class, AppModule.class, AuthStorageModule.class})
public interface AppComponent {
    void inject(RxOrderServiceImpl context);
    void inject(ShopsDataProviderImpl shopsDataProvider);
    void inject(AuthDataProviderImpl authDataProvider);
    void inject(RegisterDataProviderImpl registerDataProvider);
    void inject(ApiService apiService);
    void inject(MainActivity mainActivity);
}