package com.grishberg.viper_rest_android.data.rest.response;

import com.grishberg.viper_rest_android.domain.models.Shop;

import java.util.List;

/**
 * Created by grishberg on 12.06.16.
 */
public class ShopsResponse {
    private static final String TAG = ShopsResponse.class.getSimpleName();
    private boolean isSuccess;
    private int error;
    private String errorString;
    private List<Shop> result;

    public int getError() {
        return error;
    }

    public String getErrorString() {
        return errorString;
    }

    public List<Shop> getShopBodies() {
        return result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
