package com.rxdemo.jeff.rxdemo.presenter;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.rxdemo.jeff.rxdemo.utils.LogUtils;
import com.rxdemo.jeff.rxdemo.utils.RxUtils;
import com.rxdemo.jeff.rxdemo.utils.network.RetrofitClient;
import com.rxdemo.jeff.rxdemo.base.BasePresenter;
import com.rxdemo.jeff.rxdemo.contract.MainContract;
import com.rxdemo.jeff.rxdemo.view.MainActivity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by jeff on 17-5-2.
 */

public class MainPresenter extends BasePresenter<MainActivity> implements MainContract.Presenter {

    private final String TAG = getClass().getSimpleName();
    private final String path = "/sdcard/迈宝乐固定收款二维码/", fileName = "1488008738104.jpg";

    public MainPresenter(Object object) {
        super(object);
        mContext = (Context) object;
        mView = (MainActivity) object;
    }

    public void login(String name, String urlcode) {

        RetrofitClient.getInstance()
                .ApiService
                .login(1, name, urlcode)
                .compose(RxUtils.<JSONObject>io_main())
                .subscribeWith(new DisposableObserver<JSONObject>() {
                    @Override
                    public void onNext(JSONObject value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void uploadPhoto() {
        File file = new File(path + fileName);
        if (file.exists()) {
            RequestBody requestFile = RequestBody.create(MediaType.parse(""), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
            RetrofitClient.getInstance()
                    .ApiService
                    .uploadFile(body)
                    .compose(RxUtils.<JSONObject>io_main())
                    .subscribeWith(new DisposableObserver<JSONObject>() {
                        @Override
                        public void onNext(JSONObject value) {
                            LogUtils.debug(value.toJSONString());
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtils.debug(e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            LogUtils.debug(fileName + "不存在");
        }
    }

    public void uploadStr() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aaa", "bbb");
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toJSONString());
        RetrofitClient.getInstance().ApiService
                .uploadStr(body)
                .compose(RxUtils.<JSONObject>io_main())
                .subscribeWith(new DisposableObserver<JSONObject>() {
                    @Override
                    public void onNext(JSONObject value) {
                        LogUtils.debug(value.toJSONString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void uploadStrings() {
        Map<String, String> fields = new HashMap<>();
        fields.put("aaa", "bbb");
        fields.put("bbb", "ccc");
        RetrofitClient.getInstance()
                .ApiService
                .uploadStrs(fields)
                .compose(RxUtils.<JSONObject>io_main())
                .subscribeWith(new DisposableObserver<JSONObject>() {
                    @Override
                    public void onNext(JSONObject value) {
                        LogUtils.debug(value.toJSONString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
