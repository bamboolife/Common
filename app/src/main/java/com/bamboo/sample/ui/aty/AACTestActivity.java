package com.bamboo.sample.ui.aty;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bamboo.common.base.BaseActivity;
import com.bamboo.sample.App;
import com.bamboo.sample.R;
import com.bamboo.sample.adapter.NewsAdapter;
import com.bamboo.sample.bean.NewsData;
import com.bamboo.sample.test.MyLifeCycleObsever;
import com.bamboo.sample.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
@Route(path = "/test/aac")
public class AACTestActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private NewsViewModel mNewsViewModel;
    private List<NewsData> mNewsData;
    private NewsAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.bbl_aac_test_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mNewsData = new ArrayList<>();
        mAdapter = new NewsAdapter(mNewsData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        addObserver();
    }

    private void addObserver() {
        MyLifeCycleObsever obsever=new MyLifeCycleObsever();
        getLifecycle().addObserver(obsever);
        ViewModelProvider.AndroidViewModelFactory factory=ViewModelProvider.AndroidViewModelFactory.getInstance(App.getInstance());
        mNewsViewModel=factory.create(NewsViewModel.class);
        mNewsViewModel.getSwitcDataMap().observe(this, new Observer<NewsData>() {
            @Override
            public void onChanged(NewsData newsData) {
                assert  newsData!=null;
                mNewsData.add(newsData);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.do_btn_tv)
    public void getData() {
        mNewsViewModel.httpGetData();
    }
}
