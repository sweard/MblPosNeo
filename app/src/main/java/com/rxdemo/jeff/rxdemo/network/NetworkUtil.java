package com.rxdemo.jeff.rxdemo.network;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.rxdemo.jeff.rxdemo.App;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeff on 17-5-15.
 */

public class NetworkUtil {

    private final String BASE_URL = "http://test.mblsoft.com";

    /// RxRetrofitClient.java
    private void initClient() {

        // 创建OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                // 超时设置
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                // 错误重连
                .retryOnConnectionFailure(true)
                // 支持HTTPS
                .connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS)) //明文Http与比较新的Https
                // cookie管理
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getInstance())));

        // 添加各种插入器
        addInterceptor(builder);

        // 创建Retrofit实例
        Retrofit doubanRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                // .addConverterFactory(FastJsonConvertFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        // 创建API接口类
        doubanApi = doubanRetrofit.create(IDoubanApi.class);
    }

    private void addInterceptor(OkHttpClient.Builder builder) {
        // 添加Header
        builder.addInterceptor(new HttpHeaderInterceptor());

        // 添加缓存控制策略
        File cacheDir = App.getInstance().getExternalCacheDir();
        Cache cache = new Cache(cacheDir, DEFAULT_CACHE_SIZE);
        builder.cache(cache).addInterceptor(new HttpCacheInterceptor());

        // 添加http log
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logger);

        // 添加调试工具
        builder.networkInterceptors().add(new StethoInterceptor());
    }

}
