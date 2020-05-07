package com.bamboo.thirdparty.utils.rx;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-02-10 20:44
 * 描述：
 */
public final class RxSchedulers {
    public static <T> ObservableTransformer<T, T> apply() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> applyIO() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io());
    }
}
