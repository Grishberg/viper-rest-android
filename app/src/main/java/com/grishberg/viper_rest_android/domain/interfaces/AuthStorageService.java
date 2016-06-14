package com.grishberg.viper_rest_android.domain.interfaces;

/**
 * Created by grishberg on 12.06.16.
 */
public interface AuthStorageService {
    String getRefreshToken();
    void setRefreshToken(String refreshToken);
}
