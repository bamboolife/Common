package com.bamboo.sample.mvp.module;

import com.bamboo.common.utils.LogUtil;

import dagger.Module;
import dagger.Provides;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/3/10 5:40 PM
 * 描述：
 */
@Module
public class HomeModule {

    @Provides
    static LogUtil provideLogUtil(){
        return new LogUtil();
    }
}
