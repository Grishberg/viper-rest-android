package com.grishberg.viper_rest_android.data.providers;

import com.grishberg.viper_rest_android.data.RxApiService;
import com.grishberg.viper_rest_android.domain.interfaces.auth.RegisterDataProvider;
import com.grishberg.viper_rest_android.domain.models.RegistrationContainer;
import com.grishberg.viper_rest_android.presentation.main.App;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by grishberg on 13.06.16.
 */
public class RegisterDataProviderImpl implements RegisterDataProvider {
    private static final String TAG = RegisterDataProviderImpl.class.getSimpleName();

    @Inject
    RxApiService apiService;

    public RegisterDataProviderImpl() {
        App.getAppComponent().inject(this);
    }

    @Override
    public Observable<String> register(RegistrationContainer registrationContainer) {
        return apiService.register(registrationContainer);
    }
}
