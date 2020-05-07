package com.bamboo.sample.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bamboo.sample.R;
import com.bamboo.sample.bean.HeadBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/5/7 4:56 PM
 * 描述：
 */
public class PileAdapter extends BaseQuickAdapter< HeadBean,BaseViewHolder> {

    public PileAdapter( @Nullable List<HeadBean> data) {
        super(R.layout.bbl_pile_item_layout, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HeadBean item) {
        ImageView imageView=helper.getView(R.id.circleImage);
        Glide.with(mContext).load(item.getUrl()).into(imageView);
        if (getParentPosition(item)==0){
            setMargins(imageView,0,0,0,0);
        }

    }

    public void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

}
