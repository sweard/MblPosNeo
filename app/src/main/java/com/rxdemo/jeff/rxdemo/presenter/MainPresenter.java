package com.rxdemo.jeff.rxdemo.presenter;

import android.content.Context;

import com.rxdemo.jeff.rxdemo.MainActivity;
import com.rxdemo.jeff.rxdemo.base.BasePresenter;
import com.rxdemo.jeff.rxdemo.view.MainContract;

/**
 * Created by jeff on 17-5-2.
 */

public class MainPresenter extends BasePresenter<MainActivity> implements MainContract.Presenter {
    public MainPresenter(Object object) {
        super(object);
        mContext = (Context) object;
        mView = (MainActivity) object;
    }
}
