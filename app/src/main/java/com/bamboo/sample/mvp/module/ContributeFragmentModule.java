package com.bamboo.sample.mvp.module;

import com.bamboo.sample.ui.home.HomeFragment;

import dagger.Module;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/3/10 5:39 PM
 * 描述：
 */
@Module(includes = {HomeModule.class})
public abstract class ContributeFragmentModule {
}
