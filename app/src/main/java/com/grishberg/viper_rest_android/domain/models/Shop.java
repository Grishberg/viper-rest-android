package com.grishberg.viper_rest_android.domain.models;

import io.realm.RealmObject;

/**
 * Created by grishberg on 09.06.16.
 */
public class Shop extends RealmObject {
    private static final String TAG = Shop.class.getSimpleName();
    private int id;
    private String name; // название салона
    private String bannerUrl; // ссылка на баннер
    private long latitude; // широта
    private long longitude; // долгота
    private String address; // адрес салона

    public Shop(int id, String name, String bannerUrl, long latitude, long longitude, String address) {
        this.id = id;
        this.name = name;
        this.bannerUrl = bannerUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

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

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
