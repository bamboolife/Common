package com.bamboo.sample.mvp.module;

import android.content.Context;

import com.bamboo.sample.App;

import dagger.Binds;
import dagger.Module;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/3/10 10:01 AM
 * 描述：
 */
@Module()
public abstract class AppModule {
    @Binds
    abstract Context provideContext(App application);
}
