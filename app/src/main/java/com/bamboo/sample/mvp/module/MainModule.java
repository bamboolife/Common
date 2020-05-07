package com.bamboo.sample.mvp.module;

import com.bamboo.common.utils.LogUtil;
import com.bamboo.sample.MainActivity;
import com.bamboo.sample.mvp.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/3/10 10:04 AM
 * 描述：
 */
@Module
public abstract class MainModule {
//  @ActivityScope
//    @Binds
//    abstract

//    static String provideStr(MainActivity activity){
//        return "test";
//    }

    @Provides
    static LogUtil provideLogUtil(){
        return new LogUtil();
    }
}
