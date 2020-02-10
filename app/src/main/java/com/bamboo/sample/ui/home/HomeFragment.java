package com.bamboo.sample.ui.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bamboo.common.base.BaseFragment;
import com.bamboo.sample.R;
import com.bamboo.sample.adapter.CommonAdapter;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private HomeViewModel homeViewModel;
    private CommonAdapter mAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getDatas().observe(this, commonBeans -> {
            mAdapter.setNewData(commonBeans);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new CommonAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            //ActivityOptionsCompat compat=ActivityOptionsCompat.makeScaleUpAnimation(mView,mView.getWidth()/2,mView.getHeight()/2,0,0);
            ActivityOptionsCompat compat=ActivityOptionsCompat.makeCustomAnimation(mContext,android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            ARouter.getInstance().build("/test/toast").withOptionsCompat(compat).navigation();
        });
    }
}