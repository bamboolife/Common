package com.bamboo.common.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bamboo.common.R;

/**
 * 项目名称：CommonLibrary
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-19 20:26
 * 描述：
 */
public class FadingEdgeTopRecyclerView extends RecyclerView {
    public FadingEdgeTopRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public FadingEdgeTopRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.recyclerViewStyle);
    }

    public FadingEdgeTopRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //垂直渐变
        setVerticalFadingEdgeEnabled(true);
        //阴影高度
        setFadingEdgeLength(100);
    }

    /**
     * 顶部阴影强度，这里用系统的默认效果，所以没有重写
     */
    @Override
    protected float getTopFadingEdgeStrength() {
        return super.getTopFadingEdgeStrength();
    }

    /**
     * 底部阴影强度，这里不需要，所以设置为0f
     */
    @Override
    protected float getBottomFadingEdgeStrength() {
        return 0f;
    }
}
