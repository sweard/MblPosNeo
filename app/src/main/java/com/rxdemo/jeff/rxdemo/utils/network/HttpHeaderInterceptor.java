package com.rxdemo.jeff.rxdemo.utils.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by .F on 2017/5/16.
 * 头部添加
 */

public class HttpHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("User-Agent", "Android, xxx")
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
