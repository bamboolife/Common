package com.bamboo.sample.mvp.module;

import com.bamboo.sample.MainActivity;
import com.bamboo.sample.mvp.component.BaseActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/3/10 1:07 PM
 * 描述：
 */
@Module(subcomponents = BaseActivityComponent.class)
public abstract class BaseActivityModule {
//    @Binds
//    @IntoMap
//    @ClassKey(BaseActivityComponent.class)
//    abstract AndroidInjector.Factory<?> bindYourAndroidInjectorFactory(BaseActivityComponent.Factory factory);

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity contributeMainActivityInjector();
}
