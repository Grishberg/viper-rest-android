package com.grishberg.viper_rest_android.data.providers;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.data.RxApiService;
import com.grishberg.viper_rest_android.domain.interfaces.shops.ShopsDataProvider;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.presentation.main.App;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by grishberg on 09.06.16.
 */
public class ShopsDataProviderImpl implements ShopsDataProvider {
    private static final String TAG = ShopsDataProviderImpl.class.getSimpleName();

    @Inject
    RxApiService apiService;


    public ShopsDataProviderImpl() {
        App.getAppComponent().inject(this);
    }

    @Override
    public Observable<ListResult<Shop>> getAllShops() {
        return apiService.getAllShops();
    }
}
