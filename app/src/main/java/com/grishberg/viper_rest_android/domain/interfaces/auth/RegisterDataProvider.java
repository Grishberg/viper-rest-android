package com.grishberg.viper_rest_android.domain.interfaces.auth;

import com.grishberg.viper_rest_android.domain.models.RegistrationContainer;

import rx.Observable;

/**
 * Created by grishberg on 13.06.16.
 */
public interface RegisterDataProvider {
    Observable<String> register(RegistrationContainer registrationContainer);
}
