package com.grishberg.viper_rest_android.presentation.injection;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grishberg.viper_rest_android.data.rest.HttpLoggingInterceptor;
import com.grishberg.viper_rest_android.data.rest.RestRetrofitService;
import com.grishberg.viper_rest_android.data.rest.TestStubInterceptor;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by grishberg on 13.06.16.
 */
@Module
public class TestRestModule extends RestModule {
    private static final String TAG = TestRestModule.class.getSimpleName();
    private boolean isSuccessResponses;
    public TestRestModule(String baseUrl, boolean isSuccessResponses) {
        super(baseUrl);
        this.isSuccessResponses = isSuccessResponses;
    }

    /**
     * interceptor для логирования
     *
     * @return
     */
    @Override
    Interceptor provideInterceptor() {
        TestStubInterceptor interceptor = new TestStubInterceptor(isSuccessResponses);
        return interceptor;
    }
}
