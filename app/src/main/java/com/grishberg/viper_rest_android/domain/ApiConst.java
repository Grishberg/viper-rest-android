package com.grishberg.viper_rest_android.domain;

/**
 * Created by grishberg on 12.06.16.
 */
public final class ApiConst {
    private static final String TAG = ApiConst.class.getSimpleName();
    public static final String END_POINT = "https://grishberg.pythonanywhere.com";
    public static final class Fields{
        public static final String ACCESS_TOKEN = "access_token";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
    }

    public static final class Methods{
        public static final String GET_SHOPS = "/api/clients";
        public static final String AUTH = "/api/auth";
        public static final String REGISTER = "/api/register";

    }

}
