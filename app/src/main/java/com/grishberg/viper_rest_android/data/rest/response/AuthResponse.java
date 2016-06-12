package com.grishberg.viper_rest_android.data.rest.response;

/**
 * Created by grishberg on 12.06.16.
 * Ответ от сервера после авторизации или обновления токена
 */
public class AuthResponse {
    private static final String TAG = AuthResponse.class.getSimpleName();
    private boolean success;
    private int error;
    private String errorString;
    private AuthBody result;

    public boolean isSuccess() {
        return success;
    }

    public int getError() {
        return error;
    }

    public String getErrorString() {
        return errorString;
    }

    public AuthBody getResult() {
        return result;
    }

    public static class AuthBody {
        private String accessToken;
        private String refreshToken;

        public String getAccessToken() {
            return accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }
    }
}
