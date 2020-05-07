package com.bamboo.sample.mvp.component;

import com.bamboo.common.base.BaseActivity;
import com.bamboo.sample.mvp.module.MainActivityModule;
import com.bamboo.sample.mvp.module.MainModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/3/10 1:03 PM
 * 描述：
 */
@Subcomponent(modules = {AndroidInjectionModule.class})
public interface BaseActivityComponent extends AndroidInjector<BaseActivity> {
    @Subcomponent.Factory
    public interface Factory extends AndroidInjector.Factory<BaseActivity>{}
}
