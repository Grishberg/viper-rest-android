package com.grishberg.viper_rest_android.presentation.common;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

import com.grishberg.datafacade.sevice.BaseService;
import com.grishberg.viper_rest_android.R;

import java.lang.annotation.Annotation;

import butterknife.ButterKnife;

/**
 * Created by grishberg on 09.06.16.
 */
public abstract class BaseActivity<T extends BaseService> extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private boolean isBound;
    private boolean isFirstBind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        setContentView(layout.id());
        ButterKnife.bind(this);
        serviceIntent = getServiceIntent();
        startService(serviceIntent);
        attachedFragments = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        unbindFromService();
    }

    @Override
    public void onResume() {
        super.onResume();
        bindToService();
    }

    private void bindToService() {
        if (!isBound) {
            bindService(serviceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    private void unbindFromService() {
        Log.d(TAG, String.format("unbindFromService: isBound = %b ", isBound) + this.getClass().getSimpleName());
        if (isBound) {
            unbindService(mServiceConnection);
            isBound = false;
            apiService = null;
            onUnBound();
        }
    }
}
