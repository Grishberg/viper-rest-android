package com.grishberg.viper_rest_android.domain.interactors;

import android.app.Service;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.data.ApiService;
import com.grishberg.viper_rest_android.domain.common.Interactor;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.domain.interfaces.shops.ShopsDataProvider;
import com.grishberg.viper_rest_android.presentation.injection.DomainModule;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by grishberg on 09.06.16.
 * Логика взаимодействия с данными о предприятиях
 */
public class ShopInteractor extends Interactor<ListResult<Shop>, Void> {
    private static final String TAG = ShopInteractor.class.getSimpleName();

    private final ShopsDataProvider shopsDataProvider;

    @Inject
    public ShopInteractor(@Named(DomainModule.JOB) Scheduler jobScheduler,
                          @Named(DomainModule.UI) Scheduler uiScheduler,
                          ShopsDataProvider shopsDataProvider) {
        super(jobScheduler, uiScheduler);
        this.shopsDataProvider = shopsDataProvider;
    }

    @Override
    protected Observable<ListResult<Shop>> buildObservable(Void parameter) {
        return shopsDataProvider.getAllShops();
    }
}
