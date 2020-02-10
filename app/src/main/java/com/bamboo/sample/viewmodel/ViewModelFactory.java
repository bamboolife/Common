package com.bamboo.sample.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-02-10 20:04
 * 描述：
 */
public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Context mContext;

    public ViewModelFactory(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BaseViewModel.class)) {
            return (T) new BaseViewModel(mContext);
        }
        throw new RuntimeException("unknown class :" + modelClass.getName());
    }
}
