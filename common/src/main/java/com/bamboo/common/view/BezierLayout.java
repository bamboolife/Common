package com.bamboo.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bamboo.common.utils.DensityUtils;


/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/4/13 11:38 PM
 * 描述：贝塞尔曲线
 */
public class BezierLayout extends LinearLayout {
    private int bottomPadding=60;
    private static  final String TAG="BezierLayout";
    private Path mPath;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private PointF statrPoint;
    private PointF endPoint;
    private PointF control;
    private Context mContext;
    public BezierLayout(Context context) {
        super(context);
        init(context);
    }

    public BezierLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BezierLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext=context;
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        mPath=new Path();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=getMeasuredWidth();
        mHeight=getMeasuredHeight();

        statrPoint=new PointF(0,mHeight- DensityUtils.dp2px(mContext,bottomPadding));
        endPoint=new PointF(mWidth,mHeight-DensityUtils.dp2px(mContext,bottomPadding));
        control=new PointF(mWidth/2f,mHeight);
        mPath.moveTo(0,mHeight-DensityUtils.dp2px(mContext,bottomPadding));
        mPath.lineTo(0,mHeight);
        mPath.lineTo(mWidth,mHeight);
        //
        mPath.lineTo(endPoint.x,endPoint.y);

        //贝塞尔曲线
        mPath.quadTo(control.x,control.y,statrPoint.x,statrPoint.y);
        mPath.close();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
    }
}


















