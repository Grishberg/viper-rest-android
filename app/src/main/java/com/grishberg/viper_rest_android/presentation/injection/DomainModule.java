package com.grishberg.viper_rest_android.presentation.injection;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by grishberg on 09.06.16.
 * Модуль шедулеров для выполнения в главном потоке и новом
 */
@Module
public class DomainModule {
    private static final String TAG = DomainModule.class.getSimpleName();
    public static final String JOB = "JOB";
    public static final String UI = "UI";

    @Provides
    @Singleton
    @Named(JOB)
    public Scheduler provideJobScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named(UI)
    public Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
