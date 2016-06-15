package com.grishberg.viper_rest_android.data;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.util.Log;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.data.rest.errors.BadAccessTokenException;
import com.grishberg.viper_rest_android.data.rest.response.AuthResponse;
import com.grishberg.viper_rest_android.domain.models.RegistrationContainer;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.domain.models.ShopService;
import com.grishberg.viper_rest_android.domain.models.Specialist;
import com.grishberg.viper_rest_android.presentation.main.App;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by grishberg on 10.06.16.
 * Обертка над Android.app.Service для того что бы выполнять запросы через сервис, возвращать как Observable
 */
public class RxOrderServiceImpl implements RxApiService {
    private static final String TAG = RxOrderServiceImpl.class.getSimpleName();

    private BehaviorSubject<ApiService> orderServiceSubject = BehaviorSubject.create();
    private CompositeSubscription compositeSubscription;
    private ApiService apiService;
    private boolean isBound;
    private final Object monitor = new Object();

    @Inject
    Context appContext;

    public RxOrderServiceImpl() {
        Log.d(TAG, "RxOrderServiceImpl: ");
        App.getAppComponent().inject(this);
        compositeSubscription = new CompositeSubscription();
        Intent orderServiceIntent = new Intent(appContext, ApiService.class);
        appContext.bindService(orderServiceIntent,
                orderServiceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * Обработка подключения к сервису
     */
    private ServiceConnection orderServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");
            isBound = true;
            apiService = (ApiService) ((ApiService.ApiServiceBinder) service).getService();
            orderServiceSubject.onNext(apiService);
            // если было ожидание подключения к сервису - продолжить выполнение.
            synchronized (monitor) {
                monitor.notifyAll();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            orderServiceSubject.onCompleted();
            synchronized (monitor) {
                monitor.notifyAll();
            }
        }
    };

    /**
     * Подождать пока не подключимся к севрису
     */
    private Boolean checkConnectionToService() {
        synchronized (monitor) {
            while (!isBound) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Log.e(TAG, "checkConnectionToService: ", e);
                }
            }
        }
        return isBound;
    }

    /**
     * Вернуть функцию ожидания подключения в качестве Observable
     * @return
     */
    @NonNull
    private Observable<Boolean> getCheckConnectionDefer() {
        return Observable.defer(() -> Observable.just(checkConnectionToService()));
    }

    /**
     * Формирование Observable в ГЛАВНОМ потоке
     *
     * @return
     */
    @Override
    public Observable<ListResult<Shop>> getAllShops() {
        final PublishSubject<ListResult<Shop>> shopsSubject = PublishSubject.create();
        Subscription orderSubscription =
                orderServiceSubject.subscribe(new Action1<ApiService>() {
                    @Override
                    public void call(ApiService apiService) {
                        compositeSubscription.add(apiService.getAllShops());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        shopsSubject.onError(throwable);
                    }
                });

        compositeSubscription.add(orderSubscription);

        return shopsSubject.asObservable();
    }

    @Override
    public Observable<ListResult<ShopService>> getShopService(int shoId) {
        return null;
    }

    @Override
    public Observable<ListResult<Specialist>> getSpecialistsForService(int specialistId) {
        return null;
    }

    @Override
    public Observable<String> getAuth(String login, String password) {
        Log.d(TAG, "getAuth: " + login);
        return getCheckConnectionDefer()
                .flatMap(connectionStatus -> apiService.auth(login, password));
    }

    /**
     * Регистрация пользователя
     * @param registrationContainer
     * @return
     */
    @Override
    public Observable<String> register(final RegistrationContainer registrationContainer) {
        Log.d(TAG, "register: " + registrationContainer);
        return getCheckConnectionDefer()
                        .flatMap(connectionStatus -> apiService.register(registrationContainer));
    }

    public void close() throws Exception {
        appContext.unbindService(orderServiceConnection);
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }
}