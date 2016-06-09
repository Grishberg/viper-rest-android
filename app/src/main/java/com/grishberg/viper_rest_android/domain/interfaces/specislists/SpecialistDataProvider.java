package com.grishberg.viper_rest_android.domain.interfaces.specislists;

import com.grishberg.datafacade.data.ListResult;
import com.grishberg.viper_rest_android.domain.models.Specialist;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by grishberg on 09.06.16.
 */
public interface SpecialistDataProvider {
    Observable<ListResult<Specialist>> getServices(Scheduler scheduler);
}
