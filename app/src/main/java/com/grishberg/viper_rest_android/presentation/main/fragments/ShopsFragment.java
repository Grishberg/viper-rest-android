package com.grishberg.viper_rest_android.presentation.main.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.R;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.presentation.common.BasePresenter;
import com.grishberg.viper_rest_android.presentation.common.Layout;
import com.grishberg.viper_rest_android.presentation.main.common.BaseMainFragment;
import com.grishberg.viper_rest_android.presentation.main.controllers.ShopsAdapter;
import com.grishberg.viper_rest_android.presentation.main.interfaces.ShopsView;
import com.grishberg.viper_rest_android.presentation.presenters.ShopsPresenter;

import javax.inject.Inject;

import butterknife.Bind;

@Layout(id = R.layout.fragment_shops)
public class ShopsFragment extends BaseMainFragment implements ShopsView {

    @Inject
    ShopsPresenter shopsPresenter;

    @Bind(R.id.rvShops)
    RecyclerView recyclerView;

    public ShopsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public String getTitle() {
        return getString(R.string.shops);
    }

    @Override
    public int getFabButtonIcon() {
        return 0;
    }

    @Override
    public View.OnClickListener getFabButtonAction() {
        return null;
    }

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return shopsPresenter;
    }

    @Override
    protected void inject() {
        getMainActivityComponent().inject(this);
    }

    @Override
    public void resolveTitle(String name) {

    }

    @Override
    public void setShops(ListResult<Shop> viewModels) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ShopsAdapter shopsAdapter = new ShopsAdapter(getContext(), viewModels);
        shopsAdapter.setOnItemClickListener(
                view -> shopsPresenter.shopSelected(((Shop) view.getTag()).getId()));
        recyclerView.setAdapter(shopsAdapter);
    }
}
