package com.grishberg.viper_rest_android.data;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.domain.models.ShopService;
import com.grishberg.viper_rest_android.domain.models.Specialist;

import rx.Observable;

/**
 * Created by grishberg on 10.06.16.
 */
public interface RxApiService {
    Observable<ListResult<Shop>> getAllShops();
    Observable<ListResult<ShopService>> getShopService(int shoId);
    Observable<ListResult<Specialist>> getSpecialistsForService(int specialistId);
}
