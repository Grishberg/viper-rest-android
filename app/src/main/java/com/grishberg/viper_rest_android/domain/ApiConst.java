package com.grishberg.viper_rest_android.domain;

/**
 * Created by grishberg on 12.06.16.
 */
public final class ApiConst {
    private static final String TAG = ApiConst.class.getSimpleName();
    public static final String END_POINT = "https://grishberg.pythonanywhere.com";
    public static final class Fields{
        public static final String ACCESS_TOKEN = "accessToken";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String REFRESH_TOKEN = "refreshToken";
        public static final String NAME = "name";
        public static final String SEX = "sex";
        public static final String AGE = "age";
    }

    public static final class Methods{
        public static final String LIST_SHOPS = "/api/listShops";
        public static final String LIST_SERVICES = "/api/listServices";
        public static final String LIST_SPECIALISTS = "/api/listSpecialists";
        public static final String LIST_SERVICES_SPECIALISTS = "/api/listServicesWithSpecialists";
        public static final String ADD_ORDER = "/api/addOrder";
        public static final String LIST_SLOTS = "/api/listSlots";
        public static final String AUTH = "/api/auth";
        public static final String REGISTER = "/api/register";

        public static final String REFRESH_TOKEN = "/api/refreshToken";
    }

    public final static class Errors{
        public static final int BAD_ACCESS_TOKEN = 2;
    }
}
