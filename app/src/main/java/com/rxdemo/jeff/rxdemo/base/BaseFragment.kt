package com.rxdemo.jeff.rxdemo.base

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

abstract class BaseFragment : Fragment(), View.OnTouchListener {

    protected var toast: Toast? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(viewResid, container, false)
        view.setOnTouchListener(this)//点击其他部位隐藏小键盘；防止Fragment点击穿透
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        view!!.setOnTouchListener(this)//解决Fragmen点击穿透
        init()
        loadDatas()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getDatas(arguments)
    }

    protected fun getDatas(arguments: Bundle) {}

    protected fun init() {}

    protected fun loadDatas() {}

    protected abstract val viewResid: Int

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {

        val manager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            if (activity.currentFocus != null && activity.currentFocus!!.windowToken != null) {
                manager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        return true
    }

    //findViewById
    protected fun <H : View> fdv(resId: Int): H {
        return view!!.findViewById(resId) as H
    }

    protected fun showDialog(msg: String) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(msg)
                .setPositiveButton("OK") { dialog, which -> dialog.dismiss() }.create().show()
    }


    protected fun maktToast(msg: String): Toast {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            toast!!.setGravity(Gravity.CENTER, 0, 0)
        } else {
            toast!!.setText(msg)
        }
        return toast as Toast
    }
}
