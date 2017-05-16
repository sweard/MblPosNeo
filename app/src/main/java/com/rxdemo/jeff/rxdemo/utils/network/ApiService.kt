package com.rxdemo.jeff.rxdemo.utils.network

import com.alibaba.fastjson.JSONObject
import com.rxdemo.jeff.rxdemo.bean.GeoBean
import io.reactivex.Observable

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by .F on 2017/5/3.
 */

interface ApiService {
    @GET("geocoding?")
    fun getInfo(@Query("a") city: String, @Query("aa") area: String, @Query("aaa") town: String): Observable<GeoBean>

    @GET("api/login/LoginFuc")
    fun login(@Query("flag") flag: Int, @Query("username") name: String, @Query("urlcode") urlcode: String): Observable<JSONObject>
}
