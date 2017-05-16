package com.rxdemo.jeff.rxdemo;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by jeff on 17-5-16.
 */

public class App extends Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Stetho.initializeWithDefaults(this);//网络调试工具  调试地址：chrome://inspect
    }

    public static Context getInstance() {
        return mInstance;
    }
}
