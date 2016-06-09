package com.grishberg.datafacade.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.grishberg.datafacade.data.DataReceiveObserver;
import com.grishberg.datafacade.data.ListResult;

/**
 * Created by grishberg on 09.06.16.
 * Базовый класс RecyclerView который работает с асинхронными запросами к БД
 * Через интерфейс ListResult
 */
public abstract class BaseRecyclerAdapter<T, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H>
        implements DataReceiveObserver {
    private static final String TAG = BaseRecyclerAdapter.class.getSimpleName();
    private final ListResult<T> listResult;
    private boolean isObserved;
    private final Context context;
    private View emptyView;


    public BaseRecyclerAdapter(@NonNull Context context, @NonNull ListResult<T> listResult) {
        this.context = context;
        this.listResult = listResult;
        listResult.addDataReceiveObserver(this);
        isObserved = true;

    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        if(emptyView != null && getItemCount() > 0){
            Log.d(TAG, "setEmptyView: data exists");
            hideEmptyView();
        }
    }

    protected T getItem(int position){
        return listResult.getItem(position);
    }

    @Override
    public int getItemCount() {
        return listResult.getCount();
    }

    /**
     * Событие когда асинхронный запрос выполнился
     */
    @Override
    public void onDataReceived() {
        Log.d(TAG, "onDataReceived: ");
        // скрыть прогресс если данные получены
        if(getItemCount() > 0){
            hideEmptyView();
        }
        notifyDataSetChanged();
    }

    /**
     * Подписка на обновления из бд
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        // подписываться, только если ранее не были подписаны
        if(!isObserved){
            listResult.addDataReceiveObserver(this);
            isObserved = false;
        }

    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        // если были подписаны - отписаться
        if(isObserved){
            listResult.removeDataReceiveObserver(this);
        }
    }

    public Context getContext() {
        return context;
    }

    public void hideEmptyView(){
        if(emptyView != null){
            Log.d(TAG, "hideEmptyView:");
            emptyView.setVisibility(View.GONE);
        }
    }
}
