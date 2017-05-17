package com.rxdemo.jeff.rxdemo.view;

import android.view.View;
import android.widget.EditText;

import com.rxdemo.jeff.rxdemo.R;
import com.rxdemo.jeff.rxdemo.base.BaseActivity;
import com.rxdemo.jeff.rxdemo.presenter.MainPresenter;
import com.rxdemo.jeff.rxdemo.utils.MD5Util;

/**
 * Created by .F on 2017/5/16.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private EditText phone, password;
    private MainPresenter presenter;

    @Override
    protected int getViewResid() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        presenter = new MainPresenter(this);

        phone = fdv(R.id.editText);
        password = fdv(R.id.editText2);

        fdv(R.id.ok).setOnClickListener(this);

    }


    private void login() {
        String phoneStr = phone.getText().toString();
        String passwordStr = password.getText().toString();

        String urlcode = MD5Util.getLowerMD5(phoneStr + passwordStr).substring(8, 24);

        presenter.login(phoneStr, urlcode);
    }

    @Override
    public void onClick(View view) {
//        login();
//        presenter.uploadPhoto();
//        presenter.uploadStr();
        presenter.uploadStrings();
    }
}
