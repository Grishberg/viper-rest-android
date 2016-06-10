package com.grishberg.viper_rest_android.data.providers;

import android.app.Service;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.data.ApiService;
import com.grishberg.viper_rest_android.domain.interfaces.shops.ShopsDataProvider;
import com.grishberg.viper_rest_android.domain.models.Shop;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by grishberg on 09.06.16.
 */
public class ShopsDataProviderImpl implements ShopsDataProvider {
    private static final String TAG = ShopsDataProviderImpl.class.getSimpleName();
    private ApiService apiService;
    private boolean hasIntentToGetShops;
    private BehaviorSubject<ApiService> orderServiceSubject = BehaviorSubject.create();


    @Override
    public Observable<ListResult<Shop>> getAllShops() {
        if(apiService != null){
            hasIntentToGetShops = false;
            return apiService.getAllShops();
        }
        hasIntentToGetShops = true;
        //TODO: вернуть некоторый Observable, который передадим в сервис, что бы он наполнил данными
        return null;
    }

    @Override
    public void onServiceBound(Service service) {
        apiService = (ApiService) service;
        if(hasIntentToGetShops){
            //TODO: запустить процесс извлечения салонов.
        }
    }

    @Override
    public void onServiceUnBound() {
        apiService = null;
    }
}
