package com.bamboo.sample.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;

import com.bamboo.sample.viewmodel.ShareViewModel;

/**
 * 测试类
 */
public class MyTextView extends AppCompatTextView {
    private ShareViewModel mModel;
    private Observer<String> mObserver;
    public MyTextView(Context context) {
        super(context);

    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init(getContext());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //移除observer

    }

    private void init(Context context) {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
