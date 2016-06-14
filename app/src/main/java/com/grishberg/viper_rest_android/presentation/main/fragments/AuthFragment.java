package com.grishberg.viper_rest_android.presentation.main.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.grishberg.viper_rest_android.R;
import com.grishberg.viper_rest_android.presentation.common.BasePresenter;
import com.grishberg.viper_rest_android.presentation.common.Layout;
import com.grishberg.viper_rest_android.presentation.main.common.BaseMainFragment;
import com.grishberg.viper_rest_android.presentation.main.interfaces.AuthView;
import com.grishberg.viper_rest_android.presentation.presenters.AuthPresenter;

import javax.inject.Inject;

@Layout(id = R.layout.fragment_auth)
public class AuthFragment extends BaseMainFragment implements AuthView {

    private static final String TAG = AuthFragment.class.getSimpleName();
    @Inject
    AuthPresenter authPresenter;

    public AuthFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }

    @Override
    public String getTitle() {
        return getString(R.string.auth);
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
        return authPresenter;
    }

    @Override
    protected void inject() {
        getMainActivityComponent().inject(this);
    }

    @Override
    public void setAuthResult(String refreshToken) {
        Log.d(TAG, "setAuthResult: " + refreshToken);
        if (TextUtils.isEmpty(refreshToken)) {
            Toast.makeText(getContext(), R.string.wrong_password, Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        authPresenter.authorized(refreshToken);
    }
}
