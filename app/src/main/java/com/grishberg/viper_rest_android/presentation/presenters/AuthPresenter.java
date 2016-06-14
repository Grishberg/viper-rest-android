package com.grishberg.viper_rest_android.presentation.presenters;

import android.util.Log;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.R;
import com.grishberg.viper_rest_android.domain.interactors.AuthInteractor;
import com.grishberg.viper_rest_android.domain.interfaces.AuthStorageService;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.presentation.main.common.BaseMainPresenter;
import com.grishberg.viper_rest_android.presentation.main.interfaces.AuthView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by grishberg on 13.06.16.
 */
public class AuthPresenter extends BaseMainPresenter<AuthView> {
    private static final String TAG = AuthPresenter.class.getSimpleName();

    private final AuthInteractor authInteractor;
    private final AuthStorageService storageService;

    @Inject
    public AuthPresenter(AuthInteractor authInteractor, AuthStorageService storageService) {
        Log.d(TAG, "ShopsPresenter: ");
        this.authInteractor = authInteractor;
        this.storageService = storageService;
    }

    @Override
    public void onStart() {
        authInteractor.execute(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                getView().showError(R.string.error);
            }

            @Override
            public void onNext(String refreshToken) {
                Log.d(TAG, "onNext: ");
                getView().setAuthResult(refreshToken);
            }
        });
    }

    @Override
    public void onStop() {
        authInteractor.unsubscribe();
    }


    /**
     * Действие после авторизации
     * @param refreshToken
     */
    public void authorized(String refreshToken) {
        storageService.setRefreshToken(refreshToken);
        getRouter().openShopsList();
    }
}
