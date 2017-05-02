package com.rxdemo.jeff.rxdemo.base;

import android.content.Context;

public abstract class BasePresenter<T> {

    protected Context mContext;
    protected T mView;

    public BasePresenter(Object object) {
        mView = (T) object;
        mContext = (Context) object;
    }

    protected void stop() {

    }
}
