package com.grishberg.viper_rest_android.data.rest.errors;

/**
 * Created by grishberg on 15.06.16.
 */
public class BadRequestException extends Exception{
    private static final String TAG = BadRequestException.class.getSimpleName();

    public BadRequestException(String errorString) {
        super(errorString);
    }
}
