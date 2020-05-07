package com.bamboo.sample.mvp.module;

import com.bamboo.sample.MainActivity;
import com.bamboo.sample.mvp.scope.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/3/10 10:18 AM
 * 描述：
 */
@Module
public abstract class MainActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity contributeFeatureActivityInjector();
}
