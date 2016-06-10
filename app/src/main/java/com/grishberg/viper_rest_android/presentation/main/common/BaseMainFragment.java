package com.grishberg.viper_rest_android.presentation.main.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.grishberg.viper_rest_android.R;
import com.grishberg.viper_rest_android.presentation.common.BaseFragment;
import com.grishberg.viper_rest_android.presentation.injection.MainActivityComponent;
import com.grishberg.viper_rest_android.presentation.main.MainActivity;

/**
 * Created by grishberg on 10.06.16.
 */
public abstract class BaseMainFragment extends BaseFragment implements BaseMainView {

    public abstract String getTitle();

    @DrawableRes
    public abstract int getFabButtonIcon();

    public abstract View.OnClickListener getFabButtonAction();

    @Override
    public void showError(@StringRes int message) {
        Toast.makeText(getContext(), getString(message), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        Context context = getActivity();
        if(!(context instanceof MainActivity)){
            throw new IllegalStateException("Not instance of MainActivity");
        }
        MainActivity mainActivity = (MainActivity) getActivity();
        //noinspection unchecked
        getPresenter().setRouter(mainActivity);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //noinspection unchecked
        getPresenter().setRouter(null);
    }

    @Override
    public void showNewMessagesNotification() {
        //noinspection ConstantConditions
        Snackbar.make(getView(), R.string.new_message_comming, Snackbar.LENGTH_LONG).show();
    }

    protected MainActivityComponent getMainActivityComponent() {
        return ((MainActivity)getActivity()).getMainActivityComponent();
    }
}
