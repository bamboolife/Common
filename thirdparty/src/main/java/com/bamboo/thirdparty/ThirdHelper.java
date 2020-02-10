package com.bamboo.thirdparty;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.haohaohu.cachemanage.CacheUtil;
import com.haohaohu.cachemanage.CacheUtilConfig;

import butterknife.ButterKnife;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-02-10 15:49
 * 描述：文件操作工具类
 */
public class ThirdHelper {
    private static volatile ThirdHelper instance;

    private static Application mApplication;

    private ThirdHelper() {

    }

    public static ThirdHelper getInstance(Application application) {
        if (instance == null) {
            synchronized (ThirdHelper.class) {
                if (instance == null) {
                    mApplication = application;
                    instance = new ThirdHelper();
                }
            }
        }
        return instance;
    }


    public ThirdHelper initUtils() {

        return this;
    }

    public ThirdHelper initRouter(){
        if (BuildConfig.DEBUG) {
            // 打印日志,默认关闭
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace();
        }
        ARouter.init(mApplication);
        return this;
    }

    public ThirdHelper initCacheConfigDefault(){
        CacheUtilConfig cc =
                CacheUtilConfig.builder(mApplication).allowMemoryCache(true)//是否允许保存到内存
                        .allowEncrypt(false)//是否允许加密
                        .build();
        CacheUtil.init(cc);//初始化，必须调用
        return this;
    }

    public ThirdHelper initOpenButterKnifeDebug(){
        if (BuildConfig.DEBUG){
            ButterKnife.setDebug(true);
        }

        return this;
    }

    /**
     * 第三方资源销毁
     * @return
     */
    public ThirdHelper release(){
        ARouter.getInstance().destroy();
        return this;
    }
}
