package com.rxdemo.jeff.rxdemo.view

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.Nullable
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.facebook.stetho.common.LogUtil
import com.rxdemo.jeff.rxdemo.R
import com.rxdemo.jeff.rxdemo.utils.LogUtils
import org.jetbrains.anko.*

/**
 * Created by .F on 2017/5/22.
 */

class AnkoActivity : AppCompatActivity() {

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AnkoActivityUI().setContentView(this)
        LogUtils.debug("onCreate")
    }

    class AnkoActivityUI : AnkoComponent<AnkoActivity> {
        override fun createView(ui: AnkoContext<AnkoActivity>): View {
            return with(ui) {
                verticalLayout {
                    var account = editText {
                        hint = "请输入账号"
                        textSize = sp(10).toFloat()
                        textColor = R.color.colorAccent
                        hintTextColor = ContextCompat.getColor(context,R.color.colorAccent)
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                    }

                    var password = editText {
                        hint = R.string.app_name.toString()
                        textSize = 26f
                        textColor = R.color.colorAccent
                        hintTextColor = R.color.colorAccent
                    }.lparams {
                        width = wrapContent
                        height = wrapContent
                    }

                    button("button") {
                        setOnClickListener {

                            LogUtils.debug("fuck")

                        }
                    }
                }
            }
        }


    }
}
