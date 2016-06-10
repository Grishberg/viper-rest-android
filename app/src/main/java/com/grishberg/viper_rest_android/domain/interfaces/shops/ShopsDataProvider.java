package com.grishberg.viper_rest_android.domain.interfaces.shops;

import android.app.Service;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.domain.models.Shop;

import rx.Observable;

/**
 * Created by grishberg on 09.06.16.
 */
public interface ShopsDataProvider {
    void onServiceBound(Service service);
    void onServiceUnBound();
    Observable<ListResult<Shop>> getAllShops();
}
