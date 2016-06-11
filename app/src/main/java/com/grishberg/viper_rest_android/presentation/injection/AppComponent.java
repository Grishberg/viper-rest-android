package com.grishberg.viper_rest_android.presentation.injection;


import com.grishberg.viper_rest_android.data.RxOrderServiceImpl;
import com.grishberg.viper_rest_android.data.providers.ShopsDataProviderImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grishberg on 11.06.16.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(RxOrderServiceImpl context);
    void inject(ShopsDataProviderImpl apiService);
}