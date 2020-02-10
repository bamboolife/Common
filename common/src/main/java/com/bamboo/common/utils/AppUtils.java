package com.bamboo.common.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-02-10 19:25
 * 描述：进程判断 （有些第三方库开启了进程，不判断application会执行多次）
 */
public class AppUtils {

    public static synchronized boolean isMainProcess(Context context) {
        if (!TextUtils.isEmpty(getProcessName(context))) {
            if (getPackageName(context).equals(getProcessName(context))){
                return true;
            }
        }

        return false;
    }

    public static synchronized String getProcessName(@NonNull Context cxt) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        assert am != null;
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null && !runningApps.isEmpty()) {
            for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == Process.myPid()) {
                    return procInfo.processName;
                }
            }
        }

        return null;
    }

    /**
     * 获取应用程序名称
     */

    public static synchronized String getAppName(Context context) {
        try {

            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */

    public static synchronized String getVersionName(Context context) {
        try {

            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);

            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */

    public static synchronized int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);

            return packageInfo.versionCode;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;

    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */

    public static synchronized String getPackageName(Context context) {
        try {

            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);

            return packageInfo.packageName;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    /**
     * 获取图标 bitmap
     *
     * @param context
     */

    public static synchronized Bitmap getBitmap(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;

        try {
            packageManager = context.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }

        Drawable d = packageManager.getApplicationIcon(applicationInfo); //xxx根据自己的情况获取drawable
        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap bm = bd.getBitmap();

        return bm;

    }

}
