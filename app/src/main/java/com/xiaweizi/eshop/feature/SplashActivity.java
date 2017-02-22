package com.xiaweizi.eshop.feature;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.xiaweizi.eshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements Animator.AnimatorListener {

    @BindView(R.id.image_splash)
    ImageView mIvSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        // 完成视图的操作
        initView();

    }

    private void initView() {
        /**
         * 属性动画来完成：ViewPropertAnimator针对于View操作的动画的类
         * 1. 针对于View来操作的
         * 2. 简洁的链式调用，调用多个动画效果，这些是同时进行的
         * 3. 多个动画属性同时进行，UI只刷新一次，性能上更加优化
         * 4. 通过animate()拿到引用。
         */

        // 首先设置一个透明度
        mIvSplash.setAlpha(0.3f);

        // 从开始通过动画透明再变化
        mIvSplash.animate()
                .alpha(1.0f) // 设置透明度动画
                .setDuration(2000) // 设置动画持续时间
                .setListener(this) // 设置动画的监听
                .start(); // 开始动画

    }

    // 动画开始的时候调用
    @Override
    public void onAnimationStart(Animator animation) {

    }

    // 动画结束的时候调用
    @Override
    public void onAnimationEnd(Animator animation) {
        Intent intent = new Intent(this,EShopMainActivity.class);
        startActivity(intent);
        // 设置转场的效果
        overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
        finish();
    }

    // 动画取消的时候调用
    @Override
    public void onAnimationCancel(Animator animation) {

    }

    // 动画重复播放的时候调用
    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
