package com.bamboo.sample;

import com.bamboo.common.base.BaseApplication;
import com.bamboo.sample.mvp.component.DaggerAppComponent;


import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-02-16 11:44
 * 描述：
 */
public class App extends BaseApplication implements HasAndroidInjector {
    public static App instance;

    public static App getInstance() {
        return instance;
    }

    @Inject
    volatile DispatchingAndroidInjector<Object> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //DaggerAppComponent.create().inject(this);
        injectIfNecessary();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    public AndroidInjector<? extends App> applicationInjector() {
//        injectIfNecessary();
        return DaggerAppComponent.factory().create(this);
    }

    private void injectIfNecessary() {
        if (androidInjector == null) {
            synchronized (this) {
                if (androidInjector == null) {
                    @SuppressWarnings("unchecked")
                    AndroidInjector<App> applicationInjector =
                            (AndroidInjector<App>) applicationInjector();
                    applicationInjector.inject(this);
                    if (androidInjector == null) {
                        throw new IllegalStateException(
                                "The AndroidInjector returned from applicationInjector() did not inject the "
                                        + "DaggerApplication");
                    }
                }
            }
        }
    }
}
