package com.grishberg.viper_rest_android.data;

import android.app.Service;
import android.util.Log;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.datafacade.sevice.BaseService;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.domain.models.Specialist;

public class ApiService extends BaseService {
    private static final String TAG = ApiService.class.getSimpleName();

    public ApiService() {
    }

    @Override
    public void onCreate() {

    }

    // должны вызываться в потоке, отличном от главного

    /**
     * Список салонов
     *
     * @return
     */
    public ListResult<Shop> getAllShops() {
        Log.d(TAG, "getAllShops: " + Thread.currentThread());
        return null;
    }

    public ListResult<Service> getServicesForShops(int shopId) {
        return null;
    }

    public ListResult<Specialist> getSpecialistsForService(int shopId, int serviceId) {
        return null;
    }
}
