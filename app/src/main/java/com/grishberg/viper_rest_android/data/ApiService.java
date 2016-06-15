package com.grishberg.viper_rest_android.data;

import android.app.Service;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.datafacade.service.BaseService;
import com.grishberg.viper_rest_android.data.rest.RestRetrofitService;
import com.grishberg.viper_rest_android.data.rest.errors.BadAccessTokenException;
import com.grishberg.viper_rest_android.data.rest.response.AuthResponse;
import com.grishberg.viper_rest_android.data.rest.response.ShopsResponse;
import com.grishberg.viper_rest_android.domain.ApiConst;
import com.grishberg.viper_rest_android.domain.interfaces.AuthStorageService;
import com.grishberg.viper_rest_android.domain.models.RegistrationContainer;
import com.grishberg.viper_rest_android.domain.models.Shop;
import com.grishberg.viper_rest_android.domain.models.Specialist;
import com.grishberg.viper_rest_android.presentation.main.App;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Response;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ApiService extends BaseService {
    private static final String TAG = ApiService.class.getSimpleName();
    private static final long TIMEOUT_IN_SECONDS = 10000;
    private static final long RETRY_COUNT_FOR_REQUEST = 5;
    private AsyncTask<Void, Void, ListResult<Shop>> getShopTask;

    @Inject
    RestRetrofitService retrofitService;

    @Inject
    AuthStorageService authStorageService;

    private volatile String accessToken;

    public ApiService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        App.getAppComponent().inject(this);
    }

    /**
     * Список салонов
     *
     * @return
     */
    public Subscription getAllShops() {
        Log.d(TAG, "getAllShops: " + Thread.currentThread());

        final Subscription subscription =
                createGetAllShopsRequestObservable()//создали Observable с запросом
                        .timeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS) //поставили таймаут
                        .retry(RETRY_COUNT_FOR_REQUEST) //поставили кол-во повторов
                        .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<Shop>>>() {
                            @Override
                            public Observable<? extends List<Shop>> call(Throwable throwable) {
                                //return new observable here, that can rescure us from error
                                return Observable.empty();
                            }
                        })
                        .doOnError(new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e(TAG, "call: ", throwable);
                                if (throwable instanceof BadAccessTokenException) {
                                    //Нужно обновить токен
                                }
                            }
                        })
                        .subscribeOn(Schedulers.computation())
                        .subscribe();

        return subscription;
    }

    private Observable<List<Shop>> createGetAllShopsRequestObservable() {
        return Observable.create(new Observable.OnSubscribe<List<Shop>>() {
            @Override
            public void call(Subscriber<? super List<Shop>> subscriber) {
                try {
                    if (TextUtils.isEmpty(accessToken)) {
                        doRefreshToken();
                    }
                    Call<ShopsResponse> responseCall = retrofitService.getShops(accessToken);
                    Response<ShopsResponse> response = responseCall.execute();
                    ShopsResponse responseBody = response.body();
                    if (!responseBody.isSuccess()) {
                        Log.e(TAG, "call: " + responseBody.getErrorString());
                        switch (responseBody.getError()) {
                            case ApiConst.Errors.BAD_ACCESS_TOKEN:
                                doRefreshToken();
                                subscriber.onError(new BadAccessTokenException(responseBody.getErrorString()));
                                break;
                        }
                        return;
                    }
                    List<Shop> shopList = response.body().getShopBodies();
                    subscriber.onNext(shopList);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    Log.e(TAG, "call: ", e);
                }
            }
        });
    }

    private Func1<Throwable, Observable<? extends String>> createRequestErrorHandler() {
        return new Func1<Throwable, Observable<? extends String>>() {
            @Override
            public Observable<? extends String> call(Throwable throwable) {
                //return new observable here, that can rescure us from error
                return null;
            }
        };
    }

    public ListResult<Service> getServicesForShops(int shopId) {
        return null;
    }

    public ListResult<Specialist> getSpecialistsForService(int shopId, int serviceId) {
        return null;
    }

    //------------------ AUTH --------------------
    public Subscription auth(String login, String password) {
        final Subscription subscription =
                createAuthRequestObservable(login, password)//создали Observable с запросом
                        .timeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS) //поставили таймаут
                        .retry(RETRY_COUNT_FOR_REQUEST) //поставили кол-во повторов
                        .onErrorReturn(new Func1<Throwable, String>() {
                            @Override
                            public String call(Throwable throwable) {
                                Log.e(TAG, "call: ", throwable);
                                return null;
                            }
                        })
                        .doOnError(new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e(TAG, "call: ", throwable);
                                if (throwable instanceof BadAccessTokenException) {
                                    //Нужно обновить токен
                                }
                            }
                        })
                        .subscribeOn(Schedulers.computation())
                        .subscribe();

        return subscription;
    }

    /**
     * Запрос на регистрацию, выполняется НЕ в UI потоке
     * @param registrationContainer параметры для регистрации
     */
    public Observable<String> register(RegistrationContainer registrationContainer) {
        return createRegisterRequestObservable(registrationContainer)
                .timeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS) //поставили таймаут
                .retry(RETRY_COUNT_FOR_REQUEST) //поставили кол-во повторов
                .onErrorReturn(new Func1<Throwable, String>() {
                    @Override
                    public String call(Throwable throwable) {
                        Log.e(TAG, "call: ", throwable);
                        return null;
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(TAG, "call: ", throwable);
                    }
                });
    }

    private Observable<String> createAuthRequestObservable(String login, String password) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Call<AuthResponse> responseCall = retrofitService.auth(login, password);
                    Response<AuthResponse> response = responseCall.execute();
                    AuthResponse responseBody = response.body();
                    if (responseBody != null && !responseBody.isSuccess()) {
                        subscriber.onError(new BadAccessTokenException(responseBody.getErrorString()));
                        return;
                    }
                    if (responseBody == null) {
                        subscriber.onError(new NullPointerException());
                        return;
                    }
                    subscriber.onNext(responseBody.getResult().getRefreshToken());
                    accessToken = responseBody.getResult().getAccessToken();
                    subscriber.onCompleted();
                } catch (IOException e) {
                    Log.e(TAG, "call: ", e);
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     * Создать Observable для регистрации нового пользователя
     *
     * @return
     */
    private Observable<String> createRegisterRequestObservable(RegistrationContainer registrationContainer) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Call<AuthResponse> responseCall = retrofitService
                            .register(registrationContainer.getLogin(),
                                    registrationContainer.getPassword(),
                                    registrationContainer.getName(),
                                    registrationContainer.getSex(),
                                    registrationContainer.getAge());
                    Response<AuthResponse> response = responseCall.execute();
                    AuthResponse responseBody = response.body();
                    if (responseBody != null && !responseBody.isSuccess()) {
                        subscriber.onError(new BadAccessTokenException(responseBody.getErrorString()));
                        return;
                    }
                    if (responseBody == null || responseBody.getResult() == null) {
                        subscriber.onError(new NullPointerException());
                        return;
                    }
                    subscriber.onNext(responseBody.getResult().getRefreshToken());
                    accessToken = responseBody.getResult().getAccessToken();
                    subscriber.onCompleted();
                } catch (IOException e) {
                    Log.e(TAG, "call: ", e);
                    subscriber.onError(e);
                }
            }
        });
    }

    /**
     * Вернуть рефреш токен для обновления accessToken
     *
     * @return
     */
    private String getRefreshToken() {
        return authStorageService.getRefreshToken();
    }

    /**
     * Обновить accessToken
     *
     * @return
     * @throws IOException
     */
    private boolean doRefreshToken() throws IOException {
        Call<AuthResponse> call = retrofitService.refreshToken(getRefreshToken());
        Response<AuthResponse> response = call.execute();
        AuthResponse authResponse = response.body();
        if (authResponse == null || !authResponse.isSuccess() || authResponse.getResult() == null) {
            Log.e(TAG, "doRefreshToken: not authorized");
            return false;
        }
        accessToken = authResponse.getResult().getAccessToken();
        authStorageService.setRefreshToken(authResponse.getResult().getRefreshToken());
        return true;
    }
}
