package com.bamboo.sample;

import android.animation.Animator;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bamboo.common.base.BaseActivity;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.img_bg)
    ImageView mImageView;

    @Override
    protected int getLayoutId() {
        return R.layout.bbl_splash_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mImageView.animate()
                .scaleXBy(0.1f)
                .scaleYBy(0.1f)
                .setDuration(4000)
                .setInterpolator(new LinearInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ARouter.getInstance().build("/test/main").navigation();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
    }
}
