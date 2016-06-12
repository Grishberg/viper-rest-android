package com.grishberg.viper_rest_android.data;

import android.app.Service;
import android.os.AsyncTask;
import android.util.Log;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.datafacade.service.BaseService;
import com.grishberg.viper_rest_android.data.rest.RestRetrofitService;
import com.grishberg.viper_rest_android.data.rest.response.ShopsResponse;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.domain.models.Specialist;
import com.grishberg.viper_rest_android.presentation.main.App;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

public class ApiService extends BaseService {
    private static final String TAG = ApiService.class.getSimpleName();
    private static final long TIMEOUT_IN_SECONDS = 10000;
    private static final long RETRY_COUNT_FOR_REQUEST = 5;
    private AsyncTask<Void, Void, ListResult<Shop>> getShopTask;

    
    RestRetrofitService retrofitService;
    private String accessToken;

    public ApiService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        //App.getRestComponent().inject(this);
    }

    /**
     * Список салонов
     *
     * @return
     */
    public Subscription getAllShops() {
        Log.d(TAG, "getAllShops: " + Thread.currentThread());

        final Subscription subscription =
                Observable.create(new Observable.OnSubscribe<List<Shop>>() {
                    @Override
                    public void call(Subscriber<? super List<Shop>> subscriber) {
                        try {
                            Call<ShopsResponse> responseCall = retrofitService.getShops(accessToken);
                            Response<ShopsResponse> response = responseCall.execute();
                            List<Shop> shopList = response.body().getShopBodies();
                            subscriber.onNext(shopList);
                            subscriber.onCompleted();
                        } catch (IOException e) {
                        }
                    }
                }) //создали Observable с запросом
                        .timeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS) //поставили таймаут
                        .retry(RETRY_COUNT_FOR_REQUEST) //поставили кол-во повторов
                        .subscribe();

        return subscription;
    }

    private Observable<List<Shop>> createGetAllShopsRequestObservable() {
        return Observable.create(new Observable.OnSubscribe<List<Shop>>() {
            @Override
            public void call(Subscriber<? super List<Shop>> subscriber) {
                try {
                    Call<ShopsResponse> responseCall = retrofitService.getShops(accessToken);
                    Response<ShopsResponse> response = responseCall.execute();
                    List<Shop> shopList = response.body().getShopBodies();
                    subscriber.onNext(shopList);
                    subscriber.onCompleted();
                } catch (IOException e) {
                }
            }
        });
    }

    private Func1<Throwable, Observable<? extends String>> createRequestErrorHandler() {
        return new Func1<Throwable, Observable<? extends String>>() {
            @Override
            public Observable<? extends String> call(Throwable throwable) {
                //return new observable here, that can rescure us from error
                return null;
            }
        };
    }

    public ListResult<Service> getServicesForShops(int shopId) {
        return null;
    }

    public ListResult<Specialist> getSpecialistsForService(int shopId, int serviceId) {
        return null;
    }
}
