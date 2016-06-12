package com.grishberg.viper_rest_android.data;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.domain.models.ShopService;
import com.grishberg.viper_rest_android.domain.models.Specialist;
import com.grishberg.viper_rest_android.presentation.main.App;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by grishberg on 10.06.16.
 * Обертка над Android.app.Service для того что бы выполнять запросы через сервис, возвращать как Observable
 */
public class RxOrderServiceImpl implements RxApiService {
    private static final String TAG = RxOrderServiceImpl.class.getSimpleName();
    // IBoundOrderService is the AIDL service
    private BehaviorSubject<ApiService> orderServiceSubject = BehaviorSubject.create();
    private CompositeSubscription compositeSubscription;

    @Inject Context appContext;

    public RxOrderServiceImpl() {
        Log.d(TAG, "RxOrderServiceImpl: ");
        App.getAppComponent().inject(this);

        compositeSubscription = new CompositeSubscription();
        Intent orderServiceIntent = new Intent(appContext, ApiService.class);
//        orderServiceIntent.setPackage("com.grishberg.viper_rest_android.data");
//        orderServiceIntent.setClassName("com.grishberg.viper_rest_android.data",
//                "com.grishberg.viper_rest_android.data.ApiService");
        appContext.bindService(orderServiceIntent,
                orderServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection orderServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");
            ApiService apiService = (ApiService) ((ApiService.ApiServiceBinder) service).getService();
            orderServiceSubject.onNext(apiService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            orderServiceSubject.onCompleted();
        }
    };

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

    public void close() throws Exception {
        appContext.unbindService(orderServiceConnection);
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }
}