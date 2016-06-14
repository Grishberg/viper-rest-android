package com.grishberg.viper_rest_android.data.providers;

import com.grishberg.viper_rest_android.data.RxApiService;
import com.grishberg.viper_rest_android.domain.interfaces.auth.AuthDataProvider;
import com.grishberg.viper_rest_android.presentation.main.App;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by grishberg on 13.06.16.
 */
public class AuthDataProviderImpl implements AuthDataProvider {
    private static final String TAG = AuthDataProviderImpl.class.getSimpleName();

    @Inject
    RxApiService apiService;

    public AuthDataProviderImpl() {
        App.getAppComponent().inject(this);
    }

    @Override
    public Observable<String> checkAuth(String login, String password) {
        return apiService.getAuth(login, password);
    }
}
