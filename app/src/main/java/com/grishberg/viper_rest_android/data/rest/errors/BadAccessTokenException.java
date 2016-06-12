package com.grishberg.viper_rest_android.data.rest.errors;

/**
 * Created by grishberg on 12.06.16.
 */
public class BadAccessTokenException extends Exception {
    private static final String TAG = BadAccessTokenException.class.getSimpleName();

    public BadAccessTokenException(String errorString) {
        super(errorString);
    }
}
