package com.bamboo.common.broadcast;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 项目名称：
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-02-10 14:21
 * 描述：广播监听管理 注：此类可以写到基类中
 */
public class ReceiverManager implements LifecycleObserver {
    private static final String TAG = "ReceiverManager";
    private final Context mContext;
    private NetworkStateChangedReceiver mReceiver;

    public ReceiverManager(LifecycleOwner lifecycleOwner, Context context) {
        this.mContext = context;
        mReceiver = new NetworkStateChangedReceiver();
        lifecycleOwner.getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        mContext.registerReceiver(mReceiver, intentFilter);
        Log.i(TAG, "registerReceiver: 开始注册广播");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void unRegisterReceiver() {
        mContext.unregisterReceiver(mReceiver);
        Log.i(TAG, "unRegisterReceiver: 注销广播");
    }
}
