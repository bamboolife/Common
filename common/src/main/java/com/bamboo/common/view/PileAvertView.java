package com.bamboo.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bamboo.common.R;
import com.bamboo.common.R2;
import com.bamboo.common.gilde.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/5/7 11:28 AM
 * 描述：
 */
public class PileAvertView extends LinearLayout {

    @BindView(R2.id.pileView)
    PileView mPileView;
    Context mContext;
    public static final int VISIBLE_COUNT = 6;//默认显示个数
    public PileAvertView(Context context) {
        this(context,null);
    }

    public PileAvertView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bbl_pile_view_layout, this);
        ButterKnife.bind(view);
    }

    public void setAvertImages(List<String> imageList) {
        setAvertImages(imageList,VISIBLE_COUNT);
    }

    //如果imageList>visiableCount,显示List最上面的几个
    public void setAvertImages(List<String> imageList,int visibleCount){
        List<String> visibleList = null;
        if (imageList.size() > visibleCount) {
            visibleList = imageList.subList(imageList.size() - 1 - visibleCount, imageList.size() - 1);
        }
        mPileView.removeAllViews();
        for (int i = 0; i < imageList.size(); i++) {
            CircleImageView image= (CircleImageView) LayoutInflater.from(mContext).inflate(R.layout.bbl_round_avert_layout, mPileView, false);
            GlideApp.with(mContext).load(imageList.get(i)).error(R.mipmap.ic_pets_white_48dp).placeholder(R.mipmap.toast_frame).into(image);
            mPileView.addView(image);
        }
    }
}
