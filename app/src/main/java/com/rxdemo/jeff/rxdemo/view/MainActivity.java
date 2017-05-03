package com.rxdemo.jeff.rxdemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rxdemo.jeff.rxdemo.R;
import com.rxdemo.jeff.rxdemo.base.BaseActivity;
import com.rxdemo.jeff.rxdemo.presenter.MainPresenter;

public class MainActivity extends BaseActivity {

    private MainPresenter presenter;

    @Override
    protected int getViewResid() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        presenter = new MainPresenter(this);

    }
}
