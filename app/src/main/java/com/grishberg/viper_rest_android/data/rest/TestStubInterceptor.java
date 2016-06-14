package com.grishberg.viper_rest_android.data.rest;

import android.util.Log;

import com.grishberg.viper_rest_android.domain.ApiConst;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.net.URI;

/**
 * Created by grishberg on 13.06.16.
 * Тестовый Interceptor который будет выдавать определенные ответы
 */
public class TestStubInterceptor implements Interceptor {
    private static final String TAG = TestStubInterceptor.class.getSimpleName();
    // FAKE RESPONSES.
    private final static String SUCCES_REGISTER = "{\"success\":true,\"result\":{\"refreshToken\":\"111\",\"accessToken\":\"222\"}}";
    private static final String SUCCESS_AUTH = "{\"success\":true,\"result\":{\"refreshToken\":\"444\",\"accessToken\":\"333\"}}";
    ;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;

        String responseString;
        // Извлечь URI запроса.
        final URI uri = chain.request().uri();
        Log.d(TAG, "intercept: " + uri);
        // извлечь запускаемый метод.
        final String path = uri.getPath();
        Log.d(TAG, "intercept: path = " + path);

        switch (path) {
            // регистрация
            case ApiConst.Methods.REGISTER:
                responseString = SUCCES_REGISTER;
                break;
            case ApiConst.Methods.AUTH:
                responseString = SUCCESS_AUTH;
            default:
                responseString = "";
        }

        response = new Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();

        return response;
    }
}