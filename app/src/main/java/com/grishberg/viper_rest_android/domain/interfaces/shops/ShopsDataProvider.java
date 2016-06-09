package com.grishberg.viper_rest_android.domain.interfaces.shops;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.domain.models.Shop;

import rx.Observable;

/**
 * Created by grishberg on 09.06.16.
 */
public interface ShopsDataProvider {
    Observable<ListResult<Shop>> getAllShops();
}
