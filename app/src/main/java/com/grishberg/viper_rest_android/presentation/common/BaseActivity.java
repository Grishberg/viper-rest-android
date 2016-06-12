package com.grishberg.viper_rest_android.presentation.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.grishberg.datafacade.service.BaseService;
import com.grishberg.viper_rest_android.R;

import java.lang.annotation.Annotation;

import butterknife.ButterKnife;

/**
 * Created by grishberg on 09.06.16.
 */
public abstract class BaseActivity<T extends BaseService> extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private Intent serviceIntent;

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

    protected abstract Intent getServiceIntent();


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
