package com.rxdemo.jeff.rxdemo.base

import android.content.Context
import android.content.DialogInterface
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

import com.jaeger.library.StatusBarUtil
import com.rxdemo.jeff.rxdemo.R


abstract class BaseActivity : AppCompatActivity() {

    protected var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewResid)
//        setStatusBar()
        init()
        loadDatas()
    }

    override fun onResume() {
        /**
         * 设置为竖屏
         */
        if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        super.onResume()
    }

  /*  protected fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setColor(this, resources.getColor(R.color.colorAccent, theme), 0)
        } else {
            StatusBarUtil.setColor(this, resources.getColor(R.color.colorAccent), 0)
        }
    }*/

    protected fun loadDatas() {

    }

    protected open fun init() {}

    protected abstract val viewResid: Int

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (currentFocus != null && currentFocus!!.windowToken != null) {
                manager.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        return super.onTouchEvent(event)
    }

    //findViewById
    protected fun <H : View> fdv(resId: Int): H {
        return findViewById(resId) as H
    }

    protected fun showDialog(msg: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(msg)
                .setPositiveButton("OK") { dialog, which -> dialog.dismiss() }.create().show()
    }


    protected fun maktToast(msg: String): Toast {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
            toast!!.setGravity(Gravity.CENTER, 0, 0)
        } else {
            toast!!.setText(msg)
        }
        return toast as Toast
    }

    override fun onPause() {
        super.onPause()
    }
}
