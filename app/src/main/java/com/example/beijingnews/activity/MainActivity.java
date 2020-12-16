package com.example.beijingnews.activity;

import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

import com.example.beijingnews.R;
import com.example.beijingnews.fragment.ContentFragment;
import com.example.beijingnews.fragment.LeftmenuFragment;
import com.example.beijingnews.utils.DensityUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    public static final String MAIN_CONTENT_TAG = "main_content_tag";
    public static final String LEFTMENU_TAG = "leftmenu_tag";

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

        initFragment();
    }

    private void initFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main_content, new ContentFragment(), MAIN_CONTENT_TAG);
        ft.replace(R.id.fl_leftmenu, new LeftmenuFragment(), LEFTMENU_TAG);
        ft.commit();
    }
}