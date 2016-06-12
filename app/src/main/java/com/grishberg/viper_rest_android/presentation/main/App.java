package com.grishberg.viper_rest_android.presentation.main;

import android.app.Application;

import com.grishberg.viper_rest_android.domain.ApiConst;
import com.grishberg.viper_rest_android.presentation.injection.AppComponent;
import com.grishberg.viper_rest_android.presentation.injection.AppModule;
import com.grishberg.viper_rest_android.presentation.injection.DaggerAppComponent;
import com.grishberg.viper_rest_android.presentation.injection.DaggerRestComponent;
import com.grishberg.viper_rest_android.presentation.injection.RestComponent;
import com.grishberg.viper_rest_android.presentation.injection.RestModule;

/**
 * Created by grishberg on 11.06.16.
 */
public class App extends Application {
    private static final String TAG = App.class.getSimpleName();

    private static AppComponent appComponent;
    private static RestComponent restComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        restComponent = DaggerRestComponent
                .builder()
                .restModule(new RestModule(ApiConst.END_POINT))
                .build();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }

    public static RestComponent getRestComponent() {
        return restComponent;
    }
}
