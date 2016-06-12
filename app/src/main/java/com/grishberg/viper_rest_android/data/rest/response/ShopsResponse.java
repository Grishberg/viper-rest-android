package com.grishberg.viper_rest_android.data.rest.response;

import com.grishberg.viper_rest_android.domain.models.Shop;

import java.util.List;

/**
 * Created by grishberg on 12.06.16.
 */
public class ShopsResponse {
    private static final String TAG = ShopsResponse.class.getSimpleName();
    private int errorCode;
    private String errorString;
    private List<Shop> shopBodies;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorString() {
        return errorString;
    }

    public List<Shop> getShopBodies() {
        return shopBodies;
    }
}
