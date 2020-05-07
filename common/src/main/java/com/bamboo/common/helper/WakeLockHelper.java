package com.bamboo.common.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;

import androidx.constraintlayout.widget.ConstraintLayout;

import static android.os.PowerManager.SCREEN_BRIGHT_WAKE_LOCK;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/4/14 5:05 PM
 * 描述：
 */
public class WakeLockHelper {
    private Context mContext;
    PowerManager.WakeLock mWakeLock;
    public WakeLockHelper(Context context){
        this.mContext=context;
    }

    @SuppressLint("InvalidWakeLockTag")
    public void openScea(){
        PowerManager pm= (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        mWakeLock=pm.newWakeLock(SCREEN_BRIGHT_WAKE_LOCK,"tag");
    }
}
