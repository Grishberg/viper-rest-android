package com.grishberg.viper_rest_android.domain.interfaces.auth;

import rx.Observable;

/**
 * Created by grishberg on 13.06.16.
 */
public interface RegisterDataProvider {
    Observable<String> register(String login, String password, String name,
                                int sex, int age);
}
