package com.rxdemo.jeff.rxdemo.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.rxdemo.jeff.rxdemo.R
import com.rxdemo.jeff.rxdemo.base.BaseActivity
import com.rxdemo.jeff.rxdemo.presenter.MainPresenter

class MainActivity : BaseActivity() {
    override val viewResid: Int
        get() = R.layout.activity_main //To change initializer of created properties use File | Settings | File Templates.

    private var presenter: MainPresenter? = null

    override fun init() {
        presenter = MainPresenter(this)

    }
}
