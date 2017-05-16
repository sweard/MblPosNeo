package com.rxdemo.jeff.rxdemo.presenter;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.rxdemo.jeff.rxdemo.utils.network.RetrofitClient;
import com.rxdemo.jeff.rxdemo.base.BasePresenter;
import com.rxdemo.jeff.rxdemo.contract.MainContract;
import com.rxdemo.jeff.rxdemo.view.MainActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by jeff on 17-5-2.
 */

public class MainPresenter extends BasePresenter<MainActivity> implements MainContract.Presenter {

    private final String TAG = getClass().getSimpleName();

    public MainPresenter(Object object) {
        super(object);
        mContext = (Context) object;
        mView = (MainActivity) object;
    }

    public void login(String name, String urlcode) {

        RetrofitClient.getInstance().login(1, name, urlcode, new Observer<JSONObject>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JSONObject value) {
                Log.d(TAG, value.toJSONString());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Complete");
            }
        });
    }

}
