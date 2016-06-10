package com.grishberg.viper_rest_android.presentation.main.interfaces;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.domain.models.Shop;

/**
 * Created by grishberg on 09.06.16.
 */
public interface ServicesView {
    void setShops(ListResult<Shop> viewModels);
}
