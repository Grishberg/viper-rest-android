package com.grishberg.viper_rest_android.domain.interfaces.auth;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.domain.models.Shop;

import rx.Observable;

/**
 * Created by grishberg on 13.06.16.
 */
public interface AuthDataProvider {
    Observable<String> checkAuth(String login, String password);
}
