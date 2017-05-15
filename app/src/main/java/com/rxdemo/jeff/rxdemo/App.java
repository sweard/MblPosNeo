package com.rxdemo.jeff.rxdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by jeff on 17-5-16.
 */

public class App extends Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static Context getInstance() {
        return mInstance;
    }
}
