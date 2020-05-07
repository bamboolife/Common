package com.bamboo.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.bamboo.common.R;

/**
 * 项目名称：
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/5/7 9:52 AM
 * 描述：头像叠加容器
 * 注释：这个是一行显示不下分行显示，如果是滑动显示可以使用recyclerview
 */
public class PileView extends ViewGroup {
    protected float vertivalSpace;//垂直间隙
    protected float pileWidth = 0;//重叠宽度
    int widthSpecMode;
    int widthSpecSize;
    int heightSpecMode;
    int heightSpecSize;
    int width = 0;//容器的宽
    int height = 0;//容器的高
    int rawWidth = 0;//当前行总宽度
    int rawHeight = 0;// 当前行高
    int rowIndex = 0; //当前行位置
    int count = 0;//孩子数量

    public PileView(Context context) {
        this(context, null);
    }

    public PileView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PileView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.PileView, defStyle, 0);
        vertivalSpace = a.getDimension(R.styleable.PileView_pv_vertivalSpace, dp2px(4));
        pileWidth = a.getDimension(R.styleable.PileView_pv_pileWidth, dp2px(10));


        a.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽高模式和大小
        widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        //AT_MOST
        count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                if (i == count - 1) {
                    //最后一个child
                    height += rawHeight;
                    width = Math.max(width, rawWidth);
                }
                continue;
            }
            onMeasureChild(i, child, widthMeasureSpec, 0, heightMeasureSpec, 0);
        }
    }

    private void onMeasureChild(int index, View child, int widthMeasureSpec, int widthUsed, int heightMeasureSpec, int heightUsed) {
        //调用measureChildWithMargins 而不是measureChild
        measureChildWithMargins(child, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed);
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
        int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
        if (rawWidth + childWidth - (rowIndex > 0 ? pileWidth : 0) > widthSpecSize - getPaddingLeft() - getPaddingRight()) {//开始换行
            width = Math.max(width, rawWidth);
            rawWidth = childWidth;
            height += rawHeight + vertivalSpace;
            rawHeight = childHeight;
            rowIndex = 0;
        } else {
            rawWidth += childWidth;
            if (rowIndex > 0) {
                rawWidth -= pileWidth;//每次减去叠加部分的宽度
            }
            rawHeight = Math.max(rawHeight, childHeight);
        }

        if (index == count - 1) {
            width = Math.max(rawWidth, width);
            height += rawHeight;
        }
        rowIndex++;
        setMeasuredDimension(widthSpecMode == MeasureSpec.EXACTLY ? widthSpecSize : width + getPaddingLeft() + getPaddingRight(),
                heightSpecMode == MeasureSpec.EXACTLY ? heightSpecSize : height + getPaddingTop() + getPaddingBottom()
        );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int viewWidth = r - l;//容器的宽度
        int leftOffset = getPaddingLeft();
        int topOffset = getPaddingTop();
        int rowMaxHeight = 0;
        int rowIndex = 0;//当前行位置
        View childView;
        for (int w = 0, count = getChildCount(); w < count; w++) {
            childView = getChildAt(w);
            if (childView.getVisibility() == GONE) continue;
            // 如果加上当前子View的宽度后超过了ViewGroup的宽度，就换行
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            //孩子占据的宽度
            int occupyWidth = lp.leftMargin + childView.getMeasuredWidth() + lp.rightMargin;
            if (leftOffset + occupyWidth + getPaddingRight() > viewWidth) {
                leftOffset = getPaddingLeft();// 回到最左边
                topOffset += rowMaxHeight + vertivalSpace;//换行
                rowMaxHeight = 0;
                rowIndex = 0;

            }
            int left = leftOffset + lp.leftMargin;
            int top = topOffset + lp.topMargin;
            int right = leftOffset+ lp.leftMargin + childView.getMeasuredWidth();
            int bottom =  topOffset + lp.topMargin + childView.getMeasuredHeight();
            childView.layout(left, top, right, bottom);
            // 横向偏移
            leftOffset += occupyWidth;
            // 试图更新本行最高View的高度
            int occupyHeight = lp.topMargin + childView.getMeasuredHeight() + lp.bottomMargin;
            if(rowIndex != count - 1){
                leftOffset -= pileWidth;
            }
            rowMaxHeight = Math.max(rowMaxHeight, occupyHeight);
            rowIndex++;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    /**
     * 在内部就直接使用系统API，以后好移植。 在外部使用用 context.getResources().getDisplayMetrics()
     *
     * @param dpValue
     * @return
     */
    public float dp2px(float dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

}
