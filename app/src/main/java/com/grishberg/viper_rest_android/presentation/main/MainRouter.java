package com.grishberg.viper_rest_android.presentation.main;

/**
 * Created by grishberg on 09.06.16.
 */
public interface MainRouter {
    void openShopsList();
    void openServicesList(int shopId);
    void openShedule(int shopId, int specialistId);
    void openSpecialistList(int shopId);
}
