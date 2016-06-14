package com.grishberg.viper_rest_android.presentation.main;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.grishberg.viper_rest_android.domain.ApiConst;
import com.grishberg.viper_rest_android.domain.interfaces.AuthStorageService;
import com.grishberg.viper_rest_android.presentation.injection.AppComponent;
import com.grishberg.viper_rest_android.presentation.injection.AppModule;
import com.grishberg.viper_rest_android.presentation.injection.AuthStorageModule;
import com.grishberg.viper_rest_android.presentation.injection.DaggerAppComponent;
import com.grishberg.viper_rest_android.presentation.injection.RestModule;

/**
 * Created by grishberg on 11.06.16.
 */
public class App extends Application implements AuthStorageService {
    private static final String TAG = App.class.getSimpleName();
    private static final String REFRESH_TOKEN_KEY = "com.grishberg.viper_rest_android.refreshToken";
    public static final String NAME = "com.grishberg.viper_rest_android";

    protected static AppComponent appComponent;
    private SharedPreferences prefs;

    @Override
    public void onCreate() {
        super.onCreate();

        prefs = this.getSharedPreferences(
                NAME, Context.MODE_PRIVATE);

        initComponents(DaggerAppComponent
                        .builder()
                        .appModule(new AppModule(this))
                        .restModule(new RestModule(ApiConst.END_POINT))
                        .authStorageModule(new AuthStorageModule(this))
                        .build()
        );
    }

    public void initComponents(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

    @Override
    public String getRefreshToken() {
        return prefs.getString(REFRESH_TOKEN_KEY, null);
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        prefs.edit().putString(REFRESH_TOKEN_KEY, refreshToken).apply();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
