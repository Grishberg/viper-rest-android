package com.grishberg.viper_rest_android.presentation.injection;

import com.grishberg.viper_rest_android.data.providers.ShopsDataProviderImpl;
import com.grishberg.viper_rest_android.domain.interfaces.services.ServicesDataProvider;
import com.grishberg.viper_rest_android.domain.interfaces.shops.ShopsDataProvider;
import com.grishberg.viper_rest_android.domain.interfaces.specislists.SpecialistDataProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grishberg on 09.06.16.
 */

@Module
public class DataModule {
    private static final String TAG = DataModule.class.getSimpleName();
    @Singleton
    @Provides
    public ShopsDataProvider provideShopsDataProvider() {
        return new ShopsDataProviderImpl();
    }
/*
    @Singleton
    @Provides
    public ServicesDataProvider provideServicesDataProvider() {
        return new ();
    }

    @Singleton
    @Provides
    public SpecialistDataProvider provideSpecialistsDataProvider() {
        return new ();
    }
*/

}
