package com.grishberg.viper_rest_android.presentation.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.grishberg.viper_rest_android.R;
import com.grishberg.viper_rest_android.data.ApiService;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.presentation.common.BaseActivity;
import com.grishberg.viper_rest_android.presentation.common.Layout;
import com.grishberg.viper_rest_android.presentation.injection.DataModule;
import com.grishberg.viper_rest_android.presentation.injection.DomainModule;
import com.grishberg.viper_rest_android.presentation.injection.MainActivityComponent;

import butterknife.Bind;

@Layout(id = R.layout.activity_main)
public class MainActivity extends BaseActivity<ApiService> implements MainRouter {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;
    private MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        mainActivityComponent =
                DaggerMainActivityComponent
                        .builder()
                        .dataModule(new DataModule())
                        .domainModule(new DomainModule())
                        .build();
    }

    @Override
    protected Intent getServiceIntent() {
        return new Intent(this, ApiService.class);
    }

    public MainActivityComponent getMainActivityComponent() {
        return mainActivityComponent;
    }

    @Override
    protected void onBound() {

    }

    @Override
    public void openShopsList() {

    }

    @Override
    public void showServices(int shopId) {
       //addBackStack(ServicesFragment.newInstance(shopId));
    }

    @Override
    public void openShedule(int shopId, int specialistId) {

    }

    @Override
    public void openSpecialistList(int shopId) {

    }
}
