package com.rxdemo.jeff.rxdemo.network;

import com.rxdemo.jeff.rxdemo.bean.GeoBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by .F on 2017/5/3.
 */

public interface ApiService {
    @GET("geocoding?")
    Call<GeoBean> getInfo(@Query("a") String city, @Query("aa") String area, @Query("aaa") String town);
}
