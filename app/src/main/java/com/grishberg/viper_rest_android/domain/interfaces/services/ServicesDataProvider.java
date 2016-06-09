package com.grishberg.viper_rest_android.domain.interfaces.services;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.domain.models.Service;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by grishberg on 09.06.16.
 */
public interface ServicesDataProvider {
    Observable<ListResult<Service>> getServices(Scheduler scheduler);

}
