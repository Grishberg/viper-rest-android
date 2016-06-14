package com.grishberg.viper_rest_android.presentation.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;

import com.google.common.base.Preconditions;
import com.grishberg.viper_rest_android.R;
import com.grishberg.viper_rest_android.data.ApiService;
import com.grishberg.viper_rest_android.domain.interfaces.AuthStorageService;
import com.grishberg.viper_rest_android.presentation.common.BaseActivity;
import com.grishberg.viper_rest_android.presentation.common.Layout;
import com.grishberg.viper_rest_android.presentation.injection.DaggerMainActivityComponent;
import com.grishberg.viper_rest_android.presentation.injection.DataModule;
import com.grishberg.viper_rest_android.presentation.injection.DomainModule;
import com.grishberg.viper_rest_android.presentation.injection.MainActivityComponent;
import com.grishberg.viper_rest_android.presentation.main.common.BaseMainFragment;
import com.grishberg.viper_rest_android.presentation.main.fragments.AuthFragment;
import com.grishberg.viper_rest_android.presentation.main.fragments.ShopsFragment;

import javax.inject.Inject;

import butterknife.Bind;

@Layout(id = R.layout.activity_main)
public class MainActivity extends BaseActivity<ApiService> implements MainRouter {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;
    private MainActivityComponent mainActivityComponent;

    @Inject
    AuthStorageService authStorageService;

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

        App.getAppComponent().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!TextUtils.isEmpty(authStorageService.getRefreshToken())){
            addBackStack(new AuthFragment());
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            addBackStack(new ShopsFragment());
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void addBackStack(BaseMainFragment fragment) {
        Preconditions.checkNotNull(fragment);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content, fragment);
        tx.addToBackStack(fragment.getFragmentName());
        tx.commit();
    }

    @Override
    protected Intent getServiceIntent() {
        return new Intent(this, ApiService.class);
    }

    public MainActivityComponent getMainActivityComponent() {
        return mainActivityComponent;
    }

    @Override
    public void openShopsList() {
        addBackStack(new ShopsFragment());
    }

    @Override
    public void showServices(int shopId) {
        //addBackStack(ServicesFragment.newInstance(shopId));
    }

    @Override
    public void openSсhedule(int shopId, int specialistId) {

    }

    @Override
    public void openSpecialistList(int shopId) {

    }
}
