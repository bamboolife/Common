package com.bamboo.common.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

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
public class BaseApplication extends Application {
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
                    .initCacheConfigDefault();
        }
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ThirdHelper.getInstance(this).release();
    }



}
