package com.bamboo.sample.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bamboo.common.base.BaseFragment;
import com.bamboo.sample.R;

public class NotificationsFragment extends BaseFragment {

    private NotificationsViewModel notificationsViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notifications;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ViewModelProvider.NewInstanceFactory factory=new ViewModelProvider.NewInstanceFactory();
        notificationsViewModel= new ViewModelProvider(this,factory).get(NotificationsViewModel.class);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
    }
}