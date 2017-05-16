package com.rxdemo.jeff.rxdemo.utils.network;

import com.alibaba.fastjson.JSONObject;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.rxdemo.jeff.rxdemo.App;
import com.rxdemo.jeff.rxdemo.utils.RxUtils;

import org.reactivestreams.Subscriber;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import okhttp3.Cache;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by jeff on 17-5-15.
 */

public class RetrofitClient {

    private final String BASE_URL = "http://test.mblsoft.com/";
    private ApiService api;
    private static RetrofitClient client;

    //获取单例
    public static RetrofitClient getInstance() {
        if (client == null) {
            client = new RetrofitClient();
        }
        return client;
    }

    //初始化
    private RetrofitClient() {

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
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        // 创建API接口类
        api = retrofit.create(ApiService.class);
    }


    private void addInterceptor(OkHttpClient.Builder builder) {
        // 添加Header
        builder.addInterceptor(new HttpHeaderInterceptor());

        // 添加缓存控制策略
        File cacheDir = App.getInstance().getExternalCacheDir();
        Cache cache = new Cache(cacheDir, 1024 * 1024 * 50);
        builder.cache(cache).addInterceptor(new HttpCacheInterceptor());

        // 添加http log
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logger);

        // 添加调试工具
        builder.networkInterceptors().add(new StethoInterceptor());
    }


    public void login(int flag, String name, String urlcode, Observer<JSONObject> observer) {
        api.login(flag, name, urlcode)
                .compose(RxUtils.<JSONObject>io_main())
                .subscribe(observer);
    }

}
