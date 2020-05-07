package com.bamboo.common.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Looper;
import android.util.Log;

import com.bamboo.common.utils.NetWorkUtils;
import com.bamboo.common.utils.toasty.Toasty;

/**
 * 项目名称：
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-02-10 14:27
 * 描述：网络状态监听广播  广播在主线程中 不要做耗时操作
 */
public class NetworkStateChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            if (!NetWorkUtils.isConnected(context)) {
                Toasty.info(context,"网络连接已断开，请检查网络").show();
                Log.i("ReceiverManager", "onReceive: wifi断开");
            }else {
                Log.i("ReceiverManager", "onReceive:网络连接 ");
            }

        }
    }

    public boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}