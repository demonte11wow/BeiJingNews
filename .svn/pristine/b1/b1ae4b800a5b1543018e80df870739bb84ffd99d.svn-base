package com.example.palo.beijinnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.palo.beijinnews.activity.GuideActivity;
import com.example.palo.beijinnews.utils.CacheUtils;

public class SplashActivity extends AppCompatActivity {

    private RelativeLayout rl_splahs_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splahs_root = (RelativeLayout) findViewById(R.id.rl_splahs_root);

        //三个动画：渐变，拉伸，旋转动画

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(2000);//播放的持续时间
        aa.setFillAfter(true);


        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(2000);//播放的持续时间
        sa.setFillAfter(true);


        RotateAnimation ra = new RotateAnimation(0,360,ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(2000);//播放的持续时间
        ra.setFillAfter(true);


        //三个动画同时播放
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(ra);
        set.addAnimation(sa);
        set.addAnimation(aa);


        //开始播放动画

        rl_splahs_root.startAnimation(set);


        //监听动画播放完成
        set.setAnimationListener(new MyAnimationListener());





    }


    class MyAnimationListener implements Animation.AnimationListener {

        /**
         * 当动画开始播放的时候回调
         * @param animation
         */
        @Override
        public void onAnimationStart(Animation animation) {

        }

        /**
         * 当动画播放结束的时候回调
         * @param animation
         */
        @Override
        public void onAnimationEnd(Animation animation) {

            //当动画播放完成进入--主页面或者引导页面

            boolean startMain = CacheUtils.getBoolean(SplashActivity.this, GuideActivity.START_MAIN);
            if(startMain){
                //进入主页面
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }else{
                //进入引导页面
                Intent intent = new Intent(SplashActivity.this,GuideActivity.class);
                startActivity(intent);
            }

            //关闭欢迎页面
            finish();


        }

        /**
         * 当动画重复播放的时候回调
         * @param animation
         */
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}
