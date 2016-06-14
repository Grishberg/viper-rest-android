package com.grishberg.viper_rest_android.presentation.main.interfaces;

import com.grishberg.viper_rest_android.presentation.main.common.BaseMainView;

/**
 * Created by grishberg on 13.06.16.
 */
public interface AuthView extends BaseMainView {
    void setAuthResult(String refreshToken);
}
