package com.bamboo.sample.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bamboo.sample.R;
import com.bamboo.sample.bean.CommonBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-02-10 19:55
 * 描述：
 */
public class BaseViewModel extends ViewModel {
    private final Context mContext;
    private MutableLiveData<List<CommonBean>> mToast;

    public BaseViewModel(Context context) {
        this.mContext=context;
        mToast = new MutableLiveData<>();

    }

    public MutableLiveData<List<CommonBean>> getDatas() {
        List<CommonBean> beans=new ArrayList<>();
        beans.add(new CommonBean(mContext.getString(R.string.error_toast)));
        beans.add(new CommonBean(mContext.getString(R.string.success_toast)));
        beans.add(new CommonBean(mContext.getString(R.string.info_toast)));
        beans.add(new CommonBean(mContext.getString(R.string.info_toast_with_formatting)));
        beans.add(new CommonBean(mContext.getString(R.string.warning_toast)));
        beans.add(new CommonBean(mContext.getString(R.string.normal_toast_without_icon)));
        beans.add(new CommonBean(mContext.getString(R.string.normal_toast_with_icon)));
        beans.add(new CommonBean(mContext.getString(R.string.custom_configuration)));
        mToast.postValue(beans);
        return mToast;
    }
}
