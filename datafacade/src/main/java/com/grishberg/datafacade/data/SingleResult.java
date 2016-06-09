package com.grishberg.datafacade.data;

/**
 * Created by grishberg on 09.06.16.
 */
public interface SingleResult<T> extends BaseResult {
    T getItem();
}
