package com.bamboo.sample.mvp.component;

import com.bamboo.sample.App;
import com.bamboo.sample.mvp.module.AppModule;
import com.bamboo.sample.mvp.module.ContributeActivityModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/3/10 9:51 AM
 * 描述：
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class,ContributeActivityModule.class})
public interface  AppComponent extends AndroidInjector<App> {
    @Component.Factory
    abstract class Factory implements AndroidInjector.Factory<App> {}
}
