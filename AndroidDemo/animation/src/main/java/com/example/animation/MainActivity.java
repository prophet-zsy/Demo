package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 帧动画、View动画、属性动画
 */

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private AnimationDrawable drawable;

    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private Ball ball;
    private Animation animation;
    private Animation animation3;
    private Animation animation4;
    private Animation animation5;
    private Animation animation6;
    private Animation animation7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        drawable = (AnimationDrawable) imageView.getBackground();  // 逐帧动画

//        以下均为补间动画（View动画），设定好变化后，计算机计算并展示其变化
        imageView2 = findViewById(R.id.image2);
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);  // 旋转

        imageView3 = findViewById(R.id.image3);
        animation3 = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);  // 淡出
        animation3.setFillAfter(true); // the transformation that this animation performed, will persist when it is finished

        imageView4 = findViewById(R.id.image4);
        animation4 = AnimationUtils.loadAnimation(this, R.anim.scale_anim);  // 缩放

        imageView5 = findViewById(R.id.image5);
        animation5 = AnimationUtils.loadAnimation(this, R.anim.translate_anim); // 移动

        imageView6 = findViewById(R.id.image6);
        animation6 = AnimationUtils.loadAnimation(this, R.anim.set_anim); // 组合动画

//        以下为属性动画
        imageView7 = findViewById(R.id.image7);  // valueAnimator

        imageView8 = findViewById(R.id.image8);  // objectAnimator

        ball = findViewById(R.id.ball);  // 使用TypeEvaluator<T> 估值器 完成动画，估值器在插值器的基础上可同时控制多个属性 （与TimeInterpolator 插值器对比，插值器修改动画在时间维度上的映射）
    }

    public void control(View view) {
        switch (view.getId()) {
            case R.id.start:
                drawable.start();
                break;
            case R.id.stop:
                drawable.stop();
                break;
            case R.id.start2:
                imageView2.startAnimation(animation);
                break;
            case R.id.start3:
                imageView3.startAnimation(animation3);
                break;
            case R.id.start4:
                imageView4.startAnimation(animation4);
                break;
            case R.id.start5:
                imageView5.startAnimation(animation5);
                break;
            case R.id.start6:
                imageView6.startAnimation(animation6);
                break;
            case R.id.start7:  // 自定义动作
                final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 400f);
                valueAnimator.setTarget(imageView7);
                valueAnimator.setDuration(3000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        imageView7.setTranslationY((Float) animation.getAnimatedValue());  // 移动
                    }
                });
                valueAnimator.start();
                break;
            case R.id.start8:  // 自定义动作
//                选择1
                final ObjectAnimator objectAnimatorScaleX = ObjectAnimator.ofFloat(imageView8, "scaleX", 0f, 1f);
                final ObjectAnimator objectAnimatorScaleY = ObjectAnimator.ofFloat(imageView8, "scaleY", 0f, 1f);
                final ObjectAnimator objectAnimatorAlpha = ObjectAnimator.ofFloat(imageView8, "alpha", 0f, 1f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimatorScaleX, objectAnimatorScaleY, objectAnimatorAlpha);
                animatorSet.setDuration(3000);
                animatorSet.start();
                break;

////                选择2   （ObjectAnimator 是 ValueAnimator 的子类）
//                final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView8, "scaleX", 0f, 1f);
//                objectAnimator.setDuration(3000);
//                objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        Float value = (Float) animation.getAnimatedValue();
//                        imageView8.setScaleX(value);
//                        imageView8.setScaleY(value);  // 逐渐变大
//                        imageView8.setAlpha(value);  // 逐渐变不透明
//                    }
//                });
//                objectAnimator.start();
//                break;
            case R.id.start9:  // 自定义动作
                // 模拟平抛运动轨迹的估值器
                class MyTypeEvaluator implements TypeEvaluator<Ball.Point> {
                    @Override
                    public Ball.Point evaluate(float fraction, Ball.Point startValue, Ball.Point endValue) {
                        float pointX = fraction * (endValue.getPointX() - startValue.getPointX()) + startValue.getPointX();
                        float pointY = fraction * fraction * (endValue.getPointY() - startValue.getPointY()) + startValue.getPointY();
                        Ball.Point point  = new Ball.Point(pointX, pointY);
                        // ToDo 您可以在此记录或做相应操作
                        return point;
                    }
                }
                final ValueAnimator valueAnimator2 = ValueAnimator.ofObject(new MyTypeEvaluator(), new Ball.Point(20.0f,20.0f), new Ball.Point(300f, 300f));
                valueAnimator2.setDuration(1000);
                valueAnimator2.setInterpolator(new LinearInterpolator());  // TimeInterpolator插值器的具体实现，表示匀速运动
                valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Ball.Point point = (Ball.Point) animation.getAnimatedValue();
                        ball.setLocation(point);  // 移动
                        ball.invalidate();
//                        Log.i("TEST", point.getPointX() + "," + point.getPointY());
                    }
                });
                valueAnimator2.start();
                break;
        }
    }
}
