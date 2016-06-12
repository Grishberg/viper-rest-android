package com.grishberg.viper_rest_android.data.rest;

import com.grishberg.viper_rest_android.data.rest.response.ShopsResponse;
import com.grishberg.viper_rest_android.domain.ApiConst;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by grishberg on 12.06.16.
 */
public interface RestRetrofitService {
    @FormUrlEncoded
    @POST(ApiConst.Methods.AUTH)
    Call<ShopsResponse> auth(@Field(ApiConst.Fields.LOGIN) String login,
                             @Field(ApiConst.Fields.PASSWORD) String password);


    @FormUrlEncoded
    @POST(ApiConst.Methods.GET_SHOPS)
    Call<ShopsResponse> getShops(@Field(ApiConst.Fields.ACCESS_TOKEN) String accessToken);

}
