package com.grishberg.viper_rest_android.data;

import android.app.Service;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.datafacade.sevice.BaseService;
import com.grishberg.viper_rest_android.domain.interfaces.shops.ShopsDataProvider;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.domain.models.Specialist;

import rx.Observable;

public abstract class ApiService extends BaseService {
    private ShopsDataProvider shopsDataProvider;

    public ApiService() {
    }

    @Override
    public void onCreate() {

    }

    // должны вызываться в потоке, отличном от главного
    /**
     * Список салонов
     * @return
     */
    public ListResult<Shop> getAllShops() {
        return null;
    }

    public ListResult<Service> getServicesForShops(int shopId) {
        return null;
    }

    public ListResult<Specialist> getSpecialistsForService(int shopId, int serviceId) {
        return null;
    }

}
