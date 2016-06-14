package com.grishberg.viper_rest_android.presentation.presenters;

import android.app.Service;
import android.util.Log;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.R;
import com.grishberg.viper_rest_android.domain.interactors.ShopInteractor;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.presentation.main.common.BaseMainPresenter;
import com.grishberg.viper_rest_android.presentation.main.interfaces.ShopsView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by grishberg on 10.06.16.
 */
public class ShopsPresenter extends BaseMainPresenter<ShopsView> {
    private static final String TAG = ShopsPresenter.class.getSimpleName();

    private final ShopInteractor shopInteractor;

    @Inject
    public ShopsPresenter(ShopInteractor shopInteractor){
        Log.d(TAG, "ShopsPresenter: ");
        this.shopInteractor = shopInteractor;
    }

    @Override
    public void onStart() {
        shopInteractor.execute(new Subscriber<ListResult<Shop>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                getView().showError(R.string.error);
            }

            @Override
            public void onNext(ListResult<Shop> shops) {
                Log.d(TAG, "onNext: ");
                getView().setShops(shops);
            }
        });
    }

    @Override
    public void onStop() {
        shopInteractor.unsubscribe();
    }

    public void shopSelected(int shopId) {
        getRouter().showServices(shopId);
    }
}
