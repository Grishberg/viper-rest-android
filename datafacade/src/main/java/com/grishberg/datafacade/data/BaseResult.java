package com.grishberg.datafacade.data;

/**
 * Created by grishberg on 09.06.16.
 */
public interface BaseResult {
    void addDataReceiveObserver(DataReceiveObserver observer);
    void removeDataReceiveObserver(DataReceiveObserver observer);
    boolean isLoaded();
    void release();

    void updateData();
}