package com.bamboo.sample.mvp.module;

import com.bamboo.sample.MainActivity;
import com.bamboo.sample.mvp.scope.FragmentScope;
import com.bamboo.sample.ui.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/3/10 5:40 PM
 * 描述：
 */
@Module
public abstract class HomeFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment contributeFeatureActivityInjector();
}
