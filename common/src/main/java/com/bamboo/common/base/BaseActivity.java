package com.bamboo.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public abstract class BaseActivity extends AppCompatActivity {//implements HasAndroidInjector

    protected Context mContext;
    protected Activity mActivity;
    private Unbinder mUnbinder=null;
//    @Inject
//    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        mContext=getApplicationContext();
        mActivity=this;
        initData();
        setContentView(getLayoutId());
        ARouter.getInstance().inject(this);
        mUnbinder= ButterKnife.bind(this);
        initViews(savedInstanceState);
        setListeners();
        loadData();
    }

    /**
     * 程序开始数据的初始化
     */
    protected void initData(){ }
    /**
     * 视图id
     * @return
     */
    protected abstract int getLayoutId();
    /**
     * 视图初始化
     * @param savedInstanceState
     */
    protected abstract void initViews(Bundle savedInstanceState);
    /**
     * 控件监听
     */
    public void setListeners() { }
    /**
     * 载入网络数据
     * 没有网络加载则不重载
     */
    protected void loadData(){ }

    /**
     * 销毁对应注册的资源
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder!=null){
            mUnbinder.unbind();
        }
    }

//    @Override
//    public AndroidInjector<Object> androidInjector() {
//        return androidInjector;
//    }
}
