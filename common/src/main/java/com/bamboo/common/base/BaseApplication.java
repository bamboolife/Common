package com.bamboo.common.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.bamboo.common.gilde.GlideApp;
import com.bamboo.common.utils.AppUtils;
import com.bamboo.thirdparty.ThirdHelper;

/**
 * 项目名称：CommonLibrary
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2019-11-15 21:58
 * 描述：Application基类
 * 注意：第三库好多开了多进程  最好判断进程初始化 要不然会重复加载多次
 */
public abstract class BaseApplication extends Application  {

    private static final String TAG = "BaseApplication";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (AppUtils.isMainProcess(this)) {
            ThirdHelper.getInstance(this)
                    .initRouter()
                    .initOpenButterKnifeDebug()
                    .initCacheConfigDefault()
                    .initRouter();

        }
    }

    /**
     * 程序退出的时候调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        ThirdHelper.getInstance(this).release();
    }
    /**
     * HOME键退出应用程序
     * 程序在内存清理的时候执行
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN){//应用切换到后台清理一次
            GlideApp.get(this).clearMemory();
        }
        //根据等级让glide自动管理
        GlideApp.get(this).trimMemory(level);
    }

    /**
     * 低内存的时候执行
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        GlideApp.get(this).clearMemory();
    }

}
