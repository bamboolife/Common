package com.bamboo.sample.ui.aty;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bamboo.common.base.BaseActivity;
import com.bamboo.common.utils.toasty.Toasty;
import com.bamboo.sample.R;
import com.bamboo.sample.adapter.CommonAdapter;
import com.bamboo.sample.bean.CommonBean;
import com.bamboo.sample.viewmodel.BaseViewModel;
import com.bamboo.sample.viewmodel.ViewModelFactory;

import java.util.List;

import butterknife.BindView;

import static android.graphics.Typeface.BOLD_ITALIC;

@Route(path = "/test/toast")
public class ToastActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private BaseViewModel mBaseViewModel;
    private CommonAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.bbl_toast_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initRecyclerView();

        mBaseViewModel= new ViewModelProvider(this,new ViewModelFactory(mContext)).get(BaseViewModel.class);
        mBaseViewModel.getDatas().observe(this, new Observer<List<CommonBean>>() {
            @Override
            public void onChanged(List<CommonBean> commonBeans) {
                mAdapter.setNewData(commonBeans);
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new CommonAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setListeners() {
        super.setListeners();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position){
                case 0:
                    Toasty.error(mContext, R.string.error_message, Toasty.LENGTH_SHORT, true).show();
                    break;
                case 1:
                    Toasty.success(mContext, R.string.success_message, Toasty.LENGTH_SHORT, true).show();
                    break;
                case 2:
                    Toasty.info(mContext, R.string.info_message, Toasty.LENGTH_SHORT, true).show();
                    break;
                case 3:
                    Toasty.warning(mContext, R.string.warning_message, Toasty.LENGTH_SHORT, true).show();
                    break;
                case 4:
                    Toasty.normal(mContext, R.string.normal_message_without_icon).show();
                    break;
                case 5:
                    Drawable icon = getResources().getDrawable(R.mipmap.ic_pets_white_48dp);
                    Toasty.normal(mContext, R.string.normal_message_with_icon, icon).show();
                    break;
                case 6:
                    Toasty.info(mContext, getFormattedMessage()).show();
                    break;
                case 7:
                    Toasty.Config.getInstance()
                            .setToastTypeface(Typeface.createFromAsset(getAssets(), "PCap Terminal.otf"))
                            .allowQueue(false)
                            .apply();
                    Toasty.custom(mContext, R.string.custom_message, getResources().getDrawable(R.drawable.laptop512),
                            android.R.color.black, android.R.color.holo_green_light, Toasty.LENGTH_SHORT, true, true).show();
                    Toasty.Config.reset(); // Use this if you want to use the configuration above only once
                    break;
            }
        });
    }

    private CharSequence getFormattedMessage() {
        final String prefix = "Formatted ";
        final String highlight = "bold italic";
        final String suffix = " text";
        SpannableStringBuilder ssb = new SpannableStringBuilder(prefix).append(highlight).append(suffix);
        int prefixLen = prefix.length();
        ssb.setSpan(new StyleSpan(BOLD_ITALIC),
                prefixLen, prefixLen + highlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }
}
