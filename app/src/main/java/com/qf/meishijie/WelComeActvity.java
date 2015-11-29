package com.qf.meishijie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.qf.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


/**
 * 欢迎页
 */
@ContentView(R.layout.activity_welcome)
public class WelComeActvity extends BaseActivity{

    @ViewInject(R.id.iv_welcome_background)
    private ImageView iv_bg;

    @ViewInject(R.id.iv_welcome_start_top)
    private ImageView iv_st;

    @ViewInject(R.id.iv_welcome_start_bottom)
    private ImageView iv_sb;

    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化欢迎页
     */
    @Override
    public void init() {
        //延迟2秒开启欢迎动画
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation1 = AnimationUtils.loadAnimation(WelComeActvity.this, R.anim.start_top_out);
                iv_st.startAnimation(animation1);

                Animation animation2 = AnimationUtils.loadAnimation(WelComeActvity.this, R.anim.start_bottom_out);
                iv_sb.startAnimation(animation2);
            }
        }, 2000);

        //延迟4秒 进入主页
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //进入首页
                startActivity(new Intent(WelComeActvity.this, MainActivity.class));
                finish();
            }
        }, 5000);
    }
}
