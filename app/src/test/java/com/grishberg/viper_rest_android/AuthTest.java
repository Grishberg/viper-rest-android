package com.grishberg.viper_rest_android;

import com.grishberg.viper_rest_android.data.providers.AuthDataProviderImpl;
import com.grishberg.viper_rest_android.domain.interfaces.auth.AuthDataProvider;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by grishberg on 13.06.16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class AuthTest {
    private static final String TAG = AuthTest.class.getSimpleName();
    public static final String TEST_LOGIN = "test";
    public static final String TEST_PASSWORD = "test";

    @Test
    public void testRegister() {
        AuthDataProvider authDataProvider = new AuthDataProviderImpl();
        int times = 10;
        TestScheduler testScheduler = Schedulers.test();
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        authDataProvider
                .checkAuth(TEST_LOGIN, TEST_PASSWORD)
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        Assert.assertEquals(times + 1, testSubscriber.getOnNextEvents().size());//one first and 0ne per second
    }
}
