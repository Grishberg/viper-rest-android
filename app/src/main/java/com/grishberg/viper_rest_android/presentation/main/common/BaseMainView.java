package com.grishberg.viper_rest_android.presentation.main.common;

import android.support.annotation.StringRes;

/**
 * Created by grishberg on 10.06.16.
 */
public interface BaseMainView {
    void showError(@StringRes int message);
    void showNewMessagesNotification();
}