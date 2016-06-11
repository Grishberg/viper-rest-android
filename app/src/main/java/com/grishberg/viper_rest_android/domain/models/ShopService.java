package com.grishberg.viper_rest_android.domain.models;

import io.realm.RealmObject;

/**
 * Created by grishberg on 09.06.16.
 */
public class ShopService extends RealmObject{
    private static final String TAG = ShopService.class.getSimpleName();
    private int id;
    private String name;

    public ShopService() {
    }
/*
    public ShopService(int id, String name) {
        this.id = id;
        this.name = name;
    }
*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
