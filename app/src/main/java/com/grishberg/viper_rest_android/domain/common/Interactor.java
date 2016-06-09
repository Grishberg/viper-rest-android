package com.grishberg.viper_rest_android.domain.common;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by grishberg on 08.06.16.
 * базовый класс бизнес-логики
 */
public abstract class Interactor<ResultType, ParameterType>  {
    private final CompositeSubscription subscription = new CompositeSubscription();
    protected final Scheduler jobScheduler;
    private final Scheduler uiScheduler;

    public Interactor(Scheduler jobScheduler, Scheduler uiScheduler) {
        this.jobScheduler = jobScheduler;
        this.uiScheduler = uiScheduler;
    }

    protected abstract Observable<ResultType> buildObservable(ParameterType parameter);

    public void execute(ParameterType parameter, Subscriber<ResultType> subscriber) {
        subscription.add(buildObservable(parameter)
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler)
                .subscribe(subscriber));
    }

    public void execute(Subscriber<ResultType> subscriber) {
        execute(null, subscriber);
    }

    public void unsubscribe() {
        subscription.clear();
    }
}
