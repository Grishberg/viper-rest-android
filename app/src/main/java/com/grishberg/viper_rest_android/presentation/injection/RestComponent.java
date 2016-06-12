package com.grishberg.viper_rest_android.presentation.injection;

import com.grishberg.viper_rest_android.data.ApiService;
import com.grishberg.viper_rest_android.data.providers.ShopsDataProviderImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by grishberg on 12.06.16.
 */
@Singleton
@Component(modules = {RestModule.class})
public interface RestComponent {
    void inject(ApiService apiService);
}
