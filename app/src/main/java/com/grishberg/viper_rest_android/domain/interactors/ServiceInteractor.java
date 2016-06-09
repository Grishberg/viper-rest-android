package com.grishberg.viper_rest_android.domain.interactors;

import com.grishberg.viper_rest_android.domain.common.Interactor;
import com.grishberg.viper_rest_android.domain.interfaces.services.ServicesDataProvider;
import com.grishberg.viper_rest_android.presentation.injection.DomainModule;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by grishberg on 09.06.16.
 * Логика извлечения списка услуг
 */
public class ServiceInteractor extends Interactor {
    private static final String TAG = ServiceInteractor.class.getSimpleName();

    private final ServicesDataProvider servicesDataProvider;

    @Inject
    public ServiceInteractor(@Named(DomainModule.JOB) Scheduler jobScheduler,
                             @Named(DomainModule.UI) Scheduler uiScheduler,
                             ServicesDataProvider servicesDataProvider) {
        super(jobScheduler, uiScheduler);
        this.servicesDataProvider = servicesDataProvider;
    }

    @Override
    protected Observable buildObservable(Object parameter) {
        return null;
    }
}
