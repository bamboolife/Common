package com.bamboo.common.provider;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentProvider;
import android.content.Context;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/3/24 10:50 AM
 * 描述：全局Context无侵入式获取 封装类
 * 注：第三方库Picasso leakcanary AutoSize都用此中方式获取
 */
public class ContextProvider {
    @SuppressLint("StaticFieldLeak")
    private static volatile ContextProvider instance;
    private Context mContext;

    private ContextProvider(Context context){
        mContext=context;
    }

    /***
     * 获取实例
     * @return
     */
    public static ContextProvider get(){
        if (instance==null){
            synchronized (ContentProvider.class){
                if (instance==null){
                    Context context=ApplicationContextProvider.context;
                    if (context==null){
                        throw new IllegalStateException("context==null");
                    }
                    instance= new ContextProvider(context);
                }
            }
        }

        return instance;
    }

    /**
     * 获取上下文
     * @return
     */
    public Context getContext(){
        return mContext;
    }

    public Application getApplication(){
        return (Application) mContext.getApplicationContext();
    }
}
