package com.grishberg.viper_rest_android.presentation.injection;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grishberg.viper_rest_android.data.rest.HttpLoggingInterceptor;
import com.grishberg.viper_rest_android.data.rest.RestRetrofitService;
import com.grishberg.viper_rest_android.domain.interfaces.AuthStorageService;
import com.grishberg.viper_rest_android.presentation.main.App;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by grishberg on 12.06.16.
 */
@Module
public class RestModule {
    private static final String TAG = RestModule.class.getSimpleName();

    private String mBaseUrl;

    // Constructor needs one parameter to instantiate.
    public RestModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    /**
     * interceptor для логирования
     *
     * @return
     */

    Interceptor provideInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        Interceptor interceptor = provideInterceptor();
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(interceptor);
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    RestRetrofitService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(RestRetrofitService.class);
    }
}
