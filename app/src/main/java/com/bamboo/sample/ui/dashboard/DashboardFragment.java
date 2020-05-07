package com.bamboo.sample.ui.dashboard;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bamboo.common.base.BaseFragment;
import com.bamboo.common.gilde.GlideApp;
import com.bamboo.common.utils.NetWorkUtils;
import com.bamboo.sample.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;

public class DashboardFragment extends BaseFragment {

    private DashboardViewModel dashboardViewModel;
    ImageView mImageView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mImageView=mView.findViewById(R.id.iv_glide);
        if (NetWorkUtils.isConnected(mContext)){
            Toast.makeText(mContext,"有网络",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(mContext,"无网络",Toast.LENGTH_LONG).show();
        }
        ViewModelProvider.NewInstanceFactory factory=new ViewModelProvider.NewInstanceFactory();
        dashboardViewModel= new ViewModelProvider(this,factory).get(DashboardViewModel.class);
        Glide.with(mContext).load("https://www.baidu.com/img/bd_logo1.png?qua=high").into(mImageView);
    }



}