package com.grishberg.viper_rest_android.presentation.main.interfaces;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.presentation.main.common.BaseMainView;

/**
 * Created by grishberg on 09.06.16.
 * Интерфейс отображения списка предприятий
 */
public interface ShopsView  extends BaseMainView{
    void resolveTitle(String name);
    void setShops(ListResult<Shop> viewModels);
}
