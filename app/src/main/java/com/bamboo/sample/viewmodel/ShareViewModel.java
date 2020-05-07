package com.bamboo.sample.viewmodel;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.bamboo.sample.bean.Data;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/4/10 6:57 AM
 * 描述：
 */
public class ShareViewModel extends LiveData {
    private MutableLiveData<Data> mData=new MutableLiveData<>();
    private LiveData<String> mMapData= Transformations.map(mData, new Function<Data, String>() {
        @Override
        public String apply(Data input) {
            return input.getValue()+input.getUnit1()+"-----"+input.getUnit2();
        }
    });
    private LiveData<String> mSitchMapData=Transformations.switchMap(mData, new Function<Data, LiveData<String>>() {
        @Override
        public LiveData<String> apply(Data value) {
            MutableLiveData<String> dataLiveData = new MutableLiveData<>();
            dataLiveData.setValue(value.getValue() + value.getUnit1() + "/" + value.getUnit2());
            return dataLiveData;
        }
    });

    public MutableLiveData<Data> getData() {
        return mData;
    }

    public void setData(Data data) {
        mData.setValue(data);
    }

    public void setValue(int value){
        mData.getValue().setValue(value);
    }

}
