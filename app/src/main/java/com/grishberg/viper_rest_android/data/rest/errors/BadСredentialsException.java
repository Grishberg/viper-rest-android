package com.grishberg.viper_rest_android.data.rest.errors;

/**
 * Created by grishberg on 15.06.16.
 */
public class BadСredentialsException extends Exception {
    private static final String TAG = BadСredentialsException.class.getSimpleName();

    public BadСredentialsException(String detailMessage) {
        super(detailMessage);
    }
}
