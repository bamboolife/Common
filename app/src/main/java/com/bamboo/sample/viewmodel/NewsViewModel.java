package com.bamboo.sample.viewmodel;

import android.app.Application;
import android.transition.TransitionManager;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.bamboo.sample.bean.NewsData;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/4/12 12:45 AM
 * 描述：
 */
public class NewsViewModel extends AndroidViewModel {
    private MutableLiveData<NewsData> mData;
    private LiveData<NewsData> switcDataMap;

    public LiveData<NewsData> getSwitcDataMap() {
        return switcDataMap;
    }

    public NewsViewModel(@NonNull Application application) {
        super(application);
        mData = new MutableLiveData<>();
        switcDataMap = Transformations.switchMap(mData, new Function<NewsData, LiveData<NewsData>>() {
            @Override
            public LiveData<NewsData> apply(NewsData input) {
                MutableLiveData<NewsData> temp = new MutableLiveData<>();
                temp.setValue(input);
                return temp;
            }
        });
    }

    /**
     * 模拟获取网络数据
     */
    public void httpGetData() {
        int len = 10;
        for (int i = 0; i < len; i++) {
            NewsData data = new NewsData();
            data.setId("id" + i);
            data.setNewsTitle("title" + i);
            data.setNewsContent("content " + i);
            data.setReadNum(i);
            mData.setValue(data);
        }
    }
}
