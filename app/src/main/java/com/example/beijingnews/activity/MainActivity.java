package com.example.beijingnews.activity;

import android.os.Bundle;

import com.example.beijingnews.R;
import com.example.beijingnews.utils.DensityUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        设置主页面
        setContentView(R.layout.activity_main);

//        设置左侧菜单
        setBehindContentView(R.layout.activity_leftmenu);

        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffset(DensityUtils.dip2px(this, 200));
    }
}