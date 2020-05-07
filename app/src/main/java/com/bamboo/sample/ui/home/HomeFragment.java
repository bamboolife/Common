package com.bamboo.sample.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bamboo.common.base.BaseFragment;
import com.bamboo.sample.R;
import com.bamboo.sample.adapter.CommonAdapter;
import com.bamboo.sample.bean.CommonBean;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    private HomeViewModel homeViewModel;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private CommonAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewModelProvider.NewInstanceFactory factory = new ViewModelProvider.NewInstanceFactory();
        homeViewModel = new ViewModelProvider(this, factory).get(HomeViewModel.class);
        homeViewModel.getDatas().observe(getViewLifecycleOwner(), commonBeans -> {
            mAdapter.setNewData(commonBeans);
        });
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
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
            CommonBean bean= (CommonBean) adapter.getItem(position);
            //ActivityOptionsCompat compat=ActivityOptionsCompat.makeScaleUpAnimation(mView,mView.getWidth()/2,mView.getHeight()/2,0,0);
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(mContext, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            ARouter.getInstance().build(bean.getType()).withOptionsCompat(compat).navigation();
        });
    }
}