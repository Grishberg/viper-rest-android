package com.grishberg.datafacade.data;

/**
 * Created by grishberg on 09.06.16.
 */
public interface DataStoreObserver {
    void onDataStored(long id);
}
