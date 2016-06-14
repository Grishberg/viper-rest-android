package com.grishberg.viper_rest_android;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.grishberg.viper_rest_android.data.providers.RegisterDataProviderImpl;
import com.grishberg.viper_rest_android.domain.ApiConst;
import com.grishberg.viper_rest_android.domain.interfaces.auth.RegisterDataProvider;
import com.grishberg.viper_rest_android.presentation.injection.AppComponent;
import com.grishberg.viper_rest_android.presentation.injection.AppModule;
import com.grishberg.viper_rest_android.presentation.injection.AuthStorageModule;
import com.grishberg.viper_rest_android.presentation.injection.DaggerAppComponent;
import com.grishberg.viper_rest_android.presentation.injection.DaggerRestComponent;
import com.grishberg.viper_rest_android.presentation.injection.MainActivityComponent;
import com.grishberg.viper_rest_android.presentation.injection.RestComponent;
import com.grishberg.viper_rest_android.presentation.injection.RestModule;
import com.grishberg.viper_rest_android.presentation.injection.TestRestModule;
import com.grishberg.viper_rest_android.presentation.main.App;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

/**
 * Тестирование регистрации
 */
public class ApplicationTest extends ApplicationTestCase<App> {
    public ApplicationTest() {
        super(App.class);
    }

}