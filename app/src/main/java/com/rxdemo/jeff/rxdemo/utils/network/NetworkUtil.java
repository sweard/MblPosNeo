package com.rxdemo.jeff.rxdemo.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.rxdemo.jeff.rxdemo.App;

/**
 * Created by .F on 2017/5/16.
 */

public class NetworkUtil {

    public static boolean isNetworkConnected() {

        ConnectivityManager connMgr = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo.isConnected();
    }

}
