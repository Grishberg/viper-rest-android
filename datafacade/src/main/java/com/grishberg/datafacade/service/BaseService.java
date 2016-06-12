package com.grishberg.datafacade.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by grishberg on 10.06.16.
 */
public abstract class BaseService extends Service {
    private static final String TAG = BaseService.class.getSimpleName();
    private ApiServiceBinder binder;

    public BaseService() {
        binder = new ApiServiceBinder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    /**
     * service container for Activity
     */
    public class ApiServiceBinder extends Binder {
        public BaseService getService() {
            return BaseService.this;
        }
    }
}
