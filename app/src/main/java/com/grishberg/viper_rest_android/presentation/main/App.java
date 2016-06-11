package com.grishberg.viper_rest_android.presentation.main;

import android.app.Application;

import com.grishberg.viper_rest_android.presentation.injection.AppComponent;
import com.grishberg.viper_rest_android.presentation.injection.AppModule;
import com.grishberg.viper_rest_android.presentation.injection.DaggerAppComponent;

/**
 * Created by grishberg on 11.06.16.
 */
public class App extends Application {
    private static final String TAG = App.class.getSimpleName();

    private static AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }
}
