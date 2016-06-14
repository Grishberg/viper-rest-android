package com.grishberg.viper_rest_android.domain.interactors;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.domain.common.Interactor;
import com.grishberg.viper_rest_android.domain.interfaces.auth.AuthDataProvider;
import com.grishberg.viper_rest_android.domain.interfaces.shops.ShopsDataProvider;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.presentation.injection.DomainModule;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by grishberg on 13.06.16.
 */
public class AuthInteractor extends Interactor<String, String[]> {
    private static final String TAG = AuthInteractor.class.getSimpleName();

    private final AuthDataProvider authDataProvider;

    @Inject
    public AuthInteractor(@Named(DomainModule.JOB) Scheduler jobScheduler,
                          @Named(DomainModule.UI) Scheduler uiScheduler,
                          AuthDataProvider authDataProvider) {
        super(jobScheduler, uiScheduler);
        this.authDataProvider = authDataProvider;
    }

    @Override
    protected Observable<String> buildObservable(String[] parameter) {
        return authDataProvider.checkAuth(parameter[0], parameter[1]);
    }
}
