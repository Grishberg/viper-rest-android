package com.grishberg.viper_rest_android;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.grishberg.viper_rest_android.data.providers.AuthDataProviderImpl;
import com.grishberg.viper_rest_android.data.providers.RegisterDataProviderImpl;
import com.grishberg.viper_rest_android.data.rest.TestStubInterceptor;
import com.grishberg.viper_rest_android.domain.interfaces.auth.AuthDataProvider;
import com.grishberg.viper_rest_android.domain.interfaces.auth.RegisterDataProvider;
import com.grishberg.viper_rest_android.domain.models.RegistrationContainer;
import com.grishberg.viper_rest_android.presentation.injection.AppModule;
import com.grishberg.viper_rest_android.presentation.injection.AuthStorageModule;
import com.grishberg.viper_rest_android.presentation.injection.DaggerAppComponent;
import com.grishberg.viper_rest_android.presentation.injection.TestRestModule;
import com.grishberg.viper_rest_android.presentation.main.App;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

/**
 * Created by grishberg on 15.06.16.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class FailAuthTest {
    private static final String TAG = FailAuthTest.class.getSimpleName();
    public static final String TEST_LOGIN = "test";
    public static final String TEST_PASSWORD = "test";
    public static final String TEST_NAME = "test";
    public static final int TEST_SEX = 0;
    public static final int TEST_AGE = 31;
    public static final String HTTP_TEST_COM = "http://test.com";

    /**
     * Инициализация тестового Retrofit сервиса
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        App app = (App) InstrumentationRegistry.getTargetContext().getApplicationContext();
        app.initComponents(DaggerAppComponent
                .builder()
                .appModule(new AppModule(app))
                .restModule(new TestRestModule(HTTP_TEST_COM, false))
                .authStorageModule(new AuthStorageModule(app))
                .build()
        );
    }

    /**
     * Тестирование регистрации
     *
     * @throws Exception
     */
    @Test
    public void testFailRegister() throws Exception {
        RegisterDataProvider registerDataProvider = new RegisterDataProviderImpl();
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        registerDataProvider
                .register(new RegistrationContainer(
                        TEST_LOGIN,
                        TEST_PASSWORD,
                        TEST_NAME, TEST_SEX, TEST_SEX))
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        List<String> refreshToken = testSubscriber.getOnNextEvents();

        Assert.assertNotNull(refreshToken);
        Assert.assertNull("test refresh token not equals", refreshToken.get(0));
    }

    /**
     * Тестирование неуспешной вторизации
     * @throws Exception
     */
    @Test
    public void testFailAuth() throws Exception {
        AuthDataProvider authDataProvider = new AuthDataProviderImpl();
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        authDataProvider
                .checkAuth(TEST_LOGIN,
                        TEST_PASSWORD)
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        List<String> refreshToken = testSubscriber.getOnNextEvents();


        Assert.assertNotNull(refreshToken);
        Assert.assertNull("test refresh token not equals", refreshToken.get(0));
    }
}
