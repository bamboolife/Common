package com.bamboo.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/4/14 11:37 PM
 * 描述：
 */
public class PathTest extends View {
    Paint mPaint;
    Path mPath;
    private float mWidth;
    private float mHeight;
    public PathTest(Context context) {
        super(context);
        init();
    }

    public PathTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        mPath=new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=getMeasuredWidth();
        mHeight=getMeasuredHeight();
        mPath.moveTo(mWidth/2,mHeight/2);
        mPath.lineTo(mWidth/2+200,mHeight/2+200);
        mPath.lineTo(mWidth/2+200,mHeight/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // canvas.translate(mWidth/2,mHeight/2);
        canvas.drawPath(mPath,mPaint);
        canvas.drawLine(mWidth/2,0,mWidth/2,mHeight,mPaint);
        canvas.drawLine(0,mHeight/2,mWidth,mHeight/2,mPaint);
    }
}
