package com.grishberg.datafacade.data;

/**
 * Created by grishberg on 09.06.16.
 */
public interface ListResult<T> extends BaseResult {
    T getItem(int index);
    int getCount();
}
