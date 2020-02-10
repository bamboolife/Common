package com.bamboo.sample.ui.home;

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
        beans.add(new CommonBean("Toast封装"));
        mDatas.postValue(beans);
        return mDatas;
    }
}