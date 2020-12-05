package com.example.beijingnews;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.beijingnews.activity.GuideActivity;
import com.example.beijingnews.activity.MainActivity;
import com.example.beijingnews.utils.CacheUtils;

public class SplashActivity extends Activity {

    public static final String START_MAIN = "start_main";
    private RelativeLayout rl_splahs_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rl_splahs_root = (RelativeLayout) findViewById(R.id.rl_splahs_root);

//        渐变动画
        AlphaAnimation mAlphaAnimation = new AlphaAnimation(0, 1);
        mAlphaAnimation.setFillAfter(true);

//        缩放动画
        ScaleAnimation mScaleAnimation = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        mScaleAnimation.setFillAfter(true);

//        旋转动画
        RotateAnimation mRotateAnimation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setFillAfter(true);

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(mAlphaAnimation);
        animationSet.addAnimation(mScaleAnimation);
        animationSet.addAnimation(mRotateAnimation);
        animationSet.setDuration(4000);

        rl_splahs_root.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(SplashActivity.this, "开始动画了！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this, START_MAIN);
                Intent intent;
                if (isStartMain) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, GuideActivity.class);
                }
                startActivity(intent);
                finish();
                Toast.makeText(SplashActivity.this, "动画结束了！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}