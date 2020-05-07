package com.bamboo.sample.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bamboo.sample.bean.CommonBean;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<CommonBean>> mDatas;

    public HomeViewModel() {
        mDatas = new MutableLiveData<>();

    }

    public MutableLiveData<List<CommonBean>> getDatas() {
        List<CommonBean> beans=new ArrayList<>();
        beans.add(new CommonBean("Toast封装","/test/toast"));
        beans.add(new CommonBean("AAC测试","/test/aac"));
        beans.add(new CommonBean("WebView","/test/wv"));
        beans.add(new CommonBean("头像叠加","/test/pv"));
        mDatas.postValue(beans);
        return mDatas;
    }
}