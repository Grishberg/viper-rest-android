package com.grishberg.viper_rest_android.presentation.main;

import com.grishberg.viper_rest_android.domain.models.Shop;

/**
 * Created by grishberg on 09.06.16.
 */
public interface MainRouter {
    void openShopsList();
    void openShedule(int shopId, int specialistId);
    void openSpecialistList(int shopId);

    void showServices(int shopId);
}
