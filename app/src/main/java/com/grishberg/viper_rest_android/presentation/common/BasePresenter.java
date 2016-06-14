package com.grishberg.viper_rest_android.presentation.common;

import android.app.Service;

/**
 * Created by grishberg on 09.06.16.
 */
public abstract class BasePresenter<View, Router>  {
    private static final String TAG = BasePresenter.class.getSimpleName();
    private View view;
    private Router router;

    public abstract void onStart();

    public abstract void onStop();

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }
}
