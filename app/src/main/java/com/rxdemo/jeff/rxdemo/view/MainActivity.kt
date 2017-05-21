package com.rxdemo.jeff.rxdemo.view

import android.view.View
import com.rxdemo.jeff.rxdemo.R
import com.rxdemo.jeff.rxdemo.base.BaseActivity
import com.rxdemo.jeff.rxdemo.presenter.MainPresenter
import com.rxdemo.jeff.rxdemo.utils.MD5Util
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by .F on 2017/5/16.
 */

class MainActivity : BaseActivity(), View.OnClickListener {

    private var presenter: MainPresenter? = null

    override val viewResid: Int
        get() = R.layout.activity_main

    override fun init() {

        presenter = MainPresenter(this)


        ok.setOnClickListener(this)

    }


    private fun login() {

        val phoneStr = editText.text.toString();
        val passwordStr = editText2.text.toString()

        val urlcode = MD5Util.getLowerMD5(phoneStr + passwordStr)!!.substring(8, 24)

        presenter!!.login(phoneStr, urlcode)
    }

    override fun onClick(view: View) {
        //        login();
        //        presenter.uploadPhoto();
        //        presenter.uploadStr();
        presenter!!.uploadStrings()
    }
}
