package com.bamboo.common.view;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.FloatRange;
import androidx.annotation.IntDef;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bamboo.common.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Random;

/**
 * 可自由指定自适应裁剪的对齐点

 */
public class CropImageView extends AppCompatImageView {

    private static final int AUTO_MOVE_ANIM_DURATION_DEFAULT = 10000;

    protected final Context context;
    private int mCropType;
    private float mPercentX;
    private float mPercentY;
    private boolean mAutoMove;
    private ValueAnimator mAutoMoveAnim = null;
    private float mCropScale;
    private int mAutoMoveDuration;
    private ValueAnimator mSmoothMoveAnim = null;
    private long mSmoothMoveAnimDuration = 200;
    private TimeInterpolator mSmoothMoveAnimInterpolator = null;

    public CropImageView(Context context) {
        this(context, null);
    }

    public CropImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initAttrs(attrs);
    }

    public void setSmoothMoveAnimDuration(@IntRange(from = 0) long smoothMoveAnimDuration) {
        mSmoothMoveAnimDuration = smoothMoveAnimDuration;
    }

    public long getSmoothMoveAnimDuration() {
        return mSmoothMoveAnimDuration;
    }

    public void setSmoothMoveAnimInterpolator(TimeInterpolator smoothMoveAnimInterpolator) {
        mSmoothMoveAnimInterpolator = smoothMoveAnimInterpolator;
    }

    public TimeInterpolator getSmoothMoveAnimInterpolator() {
        return mSmoothMoveAnimInterpolator;
    }

    public void setCropType(@Type int cropType) {
        this.mCropType = cropType;
        float[] percent = getPercentFromCropType();
        setCropPercent(percent[0], percent[1]);
    }

    public int getCropType() {
        return mCropType;
    }

    public void setCropPercent(@FloatRange(from = 0, to = 1) final float percentX,
                               @FloatRange(from = 0, to = 1) final float percentY) {
        if (mSmoothMoveAnimDuration > 0){
            if (mAutoMove){
                setAutoMove(false);
                if (mSmoothMoveAnim != null){
                    mSmoothMoveAnim.cancel();
                    mSmoothMoveAnim = null;
                }
                mSmoothMoveAnim = ValueAnimator.ofFloat(0, 1);
                mSmoothMoveAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    private float startX = mPercentX;
                    private float endX = percentX;
                    private float startY = mPercentY;
                    private float endY = percentY;

                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float f = (float) valueAnimator.getAnimatedValue();
                        mPercentX = startX + (endX - startX) * f;
                        mPercentY = startY + (endY - startY) * f;
                        invalidate();
                    }
                });
                mSmoothMoveAnim.setDuration(mSmoothMoveAnimDuration);
                mSmoothMoveAnim.setInterpolator(mSmoothMoveAnimInterpolator != null ? mSmoothMoveAnimInterpolator : new DecelerateInterpolator());
                mSmoothMoveAnim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        mSmoothMoveAnim = null;
                        setAutoMove(true);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        mSmoothMoveAnim = null;
                        setAutoMove(true);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });
                mSmoothMoveAnim.start();
            } else {
                if (mSmoothMoveAnim != null){
                    mSmoothMoveAnim.cancel();
                    mSmoothMoveAnim = null;
                }
                mSmoothMoveAnim = ValueAnimator.ofFloat(0, 1);
                mSmoothMoveAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    private float startX = mPercentX;
                    private float endX = percentX;
                    private float startY = mPercentY;
                    private float endY = percentY;

                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float f = (float) valueAnimator.getAnimatedValue();
                        mPercentX = startX + (endX - startX) * f;
                        mPercentY = startY + (endY - startY) * f;
                        invalidate();
                    }
                });
                mSmoothMoveAnim.setDuration(mSmoothMoveAnimDuration);
                mSmoothMoveAnim.setInterpolator(mSmoothMoveAnimInterpolator != null ? mSmoothMoveAnimInterpolator : new DecelerateInterpolator());
                mSmoothMoveAnim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        mSmoothMoveAnim = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        mSmoothMoveAnim = null;
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                });
                mSmoothMoveAnim.start();
            }
        } else {
            if (mAutoMove){
                setAutoMove(false);
                this.mPercentX = percentX;
                this.mPercentY = percentY;
                setAutoMove(true);
            } else {
                this.mPercentX = percentX;
                this.mPercentY = percentY;
                invalidate();
            }
        }
    }

    public float[] getCropPercent() {
        return new float[]{mPercentX, mPercentY};
    }

    public void clearCropPercent() {
        this.mPercentX = -1;
        this.mPercentY = -1;
        invalidate();
    }

    public void setAutoMove(boolean autoMove) {
        mAutoMove = autoMove;
        if (!autoMove) {
            if (mAutoMoveAnim != null) {
                mAutoMoveAnim.cancel();
                mAutoMoveAnim = null;
            }
        }
        invalidate();
    }

    public boolean isAutoMove() {
        return mAutoMove;
    }

    public void setAutoMoveDuration(int autoMoveDuration) {
        mAutoMoveDuration = autoMoveDuration;
    }

    public int getAutoMoveDuration() {
        return mAutoMoveDuration;
    }

    public void setCropScale(@FloatRange(from = 1) float cropScale) {
        mCropScale = cropScale;
        invalidate();
    }

    public float getCropScale() {
        return mCropScale;
    }

    protected void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CropImageView);
        mCropType = typedArray.getInt(R.styleable.CropImageView_crop_type, CropType.CENTER);
        mPercentX = typedArray.getFloat(R.styleable.CropImageView_crop_percent_x, -1);
        mPercentY = typedArray.getFloat(R.styleable.CropImageView_crop_percent_y, -1);
        mAutoMove = typedArray.getBoolean(R.styleable.CropImageView_crop_auto_move, false);
        mAutoMoveDuration = typedArray.getInteger(R.styleable.CropImageView_crop_auto_move_duration, AUTO_MOVE_ANIM_DURATION_DEFAULT);
        mCropScale = typedArray.getFloat(R.styleable.CropImageView_crop_scale, 1);
        typedArray.recycle();

        float[] percent = getPercentFromCropType();
        if (mPercentX == -1) {
            mPercentX = percent[0];
        }
        if (mPercentY == -1) {
            mPercentY = percent[1];
        }
        if (mAutoMoveDuration < 0) {
            mAutoMoveDuration = AUTO_MOVE_ANIM_DURATION_DEFAULT;
        }
        if (mCropScale < 1) {
            mCropScale = 1;
        }
    }

    private void initAutoMoveAnim() {
        if (!mAutoMove) {
            return;
        }
        if (mAutoMoveAnim != null) {
            return;
        }

        float[] percent = getCropPercent();
        mPercentX = percent[0];
        mPercentY = percent[1];

        final float percentX;
        final float percentY;

        Random random = new Random();
        if (mPercentX == 0) {
            if (mPercentY == 0) {
                if (random.nextBoolean()) {
                    percentX = 1;
                    percentY = random.nextFloat();
                } else {
                    percentX = random.nextFloat();
                    percentY = 1;
                }
            } else if (mPercentY == 1) {
                if (random.nextBoolean()) {
                    percentX = 1;
                    percentY = random.nextFloat();
                } else {
                    percentX = random.nextFloat();
                    percentY = 0;
                }
            } else {
                if (random.nextBoolean()) {
                    percentX = 1;
                    percentY = random.nextFloat();
                } else {
                    if (random.nextBoolean()) {
                        percentX = random.nextFloat();
                        percentY = 0;
                    } else {
                        percentX = random.nextFloat();
                        percentY = 1;
                    }
                }
            }
        } else if (mPercentX == 1) {
            if (mPercentY == 0) {
                if (random.nextBoolean()) {
                    percentX = 0;
                    percentY = random.nextFloat();
                } else {
                    percentX = random.nextFloat();
                    percentY = 1;
                }
            } else if (mPercentY == 1) {
                if (random.nextBoolean()) {
                    percentX = 0;
                    percentY = random.nextFloat();
                } else {
                    percentX = random.nextFloat();
                    percentY = 0;
                }
            } else {
                if (random.nextBoolean()) {
                    percentX = 0;
                    percentY = random.nextFloat();
                } else {
                    if (random.nextBoolean()) {
                        percentX = random.nextFloat();
                        percentY = 0;
                    } else {
                        percentX = random.nextFloat();
                        percentY = 1;
                    }
                }
            }
        } else {
            if (mPercentY == 0) {
                if (random.nextBoolean()) {
                    percentX = random.nextFloat();
                    percentY = 1;
                } else {
                    if (random.nextBoolean()) {
                        percentX = 0;
                        percentY = random.nextFloat();
                    } else {
                        percentX = 1;
                        percentY = random.nextFloat();
                    }
                }
            } else if (mPercentY == 1) {
                if (random.nextBoolean()) {
                    percentX = random.nextFloat();
                    percentY = 0;
                } else {
                    if (random.nextBoolean()) {
                        percentX = 0;
                        percentY = random.nextFloat();
                    } else {
                        percentX = 1;
                        percentY = random.nextFloat();
                    }
                }
            } else {
                if (random.nextBoolean()) {
                    if (random.nextBoolean()) {
                        percentX = 0;
                        percentY = random.nextFloat();
                    } else {
                        percentX = 1;
                        percentY = random.nextFloat();
                    }
                } else {
                    if (random.nextBoolean()) {
                        percentX = random.nextFloat();
                        percentY = 0;
                    } else {
                        percentX = random.nextFloat();
                        percentY = 1;
                    }
                }
            }
        }

        mAutoMoveAnim = ValueAnimator.ofFloat(0, 1);
        mAutoMoveAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private float startX = mPercentX;
            private float endX = percentX;
            private float startY = mPercentY;
            private float endY = percentY;

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float f = (float) valueAnimator.getAnimatedValue();
                mPercentX = startX + (endX - startX) * f;
                mPercentY = startY + (endY - startY) * f;
            }
        });
        long duration = (long) (mAutoMoveDuration * (Math.pow(Math.pow(percentX - mPercentX, 2) + Math.pow(percentY - mPercentY, 2), 0.5)) / Math.pow(2, 0.5));
        mAutoMoveAnim.setDuration(duration);
        mAutoMoveAnim.setInterpolator(new LinearInterpolator());
        mAutoMoveAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mAutoMoveAnim = null;
                initAutoMoveAnim();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                mAutoMoveAnim = null;
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        mAutoMoveAnim.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        initMatrix();
        Matrix matrix = getImageMatrix();
        if (matrix == null) {
            drawable.draw(canvas);
        } else {
            final int saveCount = canvas.getSaveCount();
            canvas.save();
            canvas.concat(matrix);
            getDrawable().draw(canvas);
            canvas.restoreToCount(saveCount);
        }
        if (mAutoMove) {
            initAutoMoveAnim();
            invalidate();
        }
    }

    private void initMatrix() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        Matrix matrix = getImageMatrix();
        if (matrix == null) {
            return;
        }

        final int dWidth = drawable.getIntrinsicWidth();
        final int dHeight = drawable.getIntrinsicHeight();

        final int vWidth = getWidth();
        final int vHeight = getHeight();

        float[] percent = getCropPercent();
        float percentX = percent[0];
        float percentY = percent[1];

        float scale;
        float dx;
        float dy;

        if (dWidth * vHeight > vWidth * dHeight) {
            scale = (float) vHeight / (float) dHeight * mCropScale;
            dx = (vWidth - dWidth * scale) * percentX;
            dy = (vHeight - dHeight * scale) * percentY;
        } else {
            scale = (float) vWidth / (float) dWidth * mCropScale;
            dx = (vWidth - dWidth * scale) * percentX;
            dy = (vHeight - dHeight * scale) * percentY;
        }

        matrix.setScale(scale, scale);
        matrix.postTranslate(Math.round(dx), Math.round(dy));
    }

    private float[] getPercentFromCropType() {
        float[] percent = new float[]{0.5f, 0.5f};
        switch (mCropType) {
            default:
                break;
            case CropType.CENTER:
                percent[0] = 0.5f;
                percent[1] = 0.5f;
                break;
            case CropType.CENTER_LEFT:
                percent[0] = 0.0f;
                percent[1] = 0.5f;
                break;
            case CropType.CENTER_RIGHT:
                percent[0] = 1.0f;
                percent[1] = 0.5f;
                break;
            case CropType.CENTER_TOP:
                percent[0] = 0.5f;
                percent[1] = 0.0f;
                break;
            case CropType.CENTER_BOTTOM:
                percent[0] = 1.0f;
                percent[1] = 0.5f;
                break;
            case CropType.TOP_LEFT:
                percent[0] = 0.0f;
                percent[1] = 0.0f;
                break;
            case CropType.BOTTOM_LEFT:
                percent[0] = 0.0f;
                percent[1] = 1.0f;
                break;
            case CropType.TOP_RIGHT:
                percent[0] = 1.0f;
                percent[1] = 0.0f;
                break;
            case CropType.BOTTOM_RIGHT:
                percent[0] = 1.0f;
                percent[1] = 1.0f;
                break;
        }
        return percent;
    }

    public interface CropType {
        int CENTER = 0;
        int CENTER_LEFT = 1;
        int CENTER_RIGHT = 2;
        int CENTER_TOP = 3;
        int CENTER_BOTTOM = 4;
        int TOP_LEFT = 5;
        int TOP_RIGHT = 6;
        int BOTTOM_LEFT = 7;
        int BOTTOM_RIGHT = 8;
    }

    @IntDef({CropType.CENTER,
            CropType.CENTER_LEFT, CropType.CENTER_RIGHT, CropType.CENTER_TOP, CropType.CENTER_BOTTOM,
            CropType.TOP_LEFT, CropType.BOTTOM_LEFT, CropType.TOP_RIGHT, CropType.BOTTOM_RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    @interface Type {
    }
}