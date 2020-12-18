package com.example.beijingnews.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.beijingnews.R;
import com.example.beijingnews.base.BaseFragment;

public class ContentFragment extends BaseFragment {

    private ViewPager viewPager;
    private RadioGroup rg_main;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.content_fragment, null);
        viewPager = view.findViewById(R.id.view_page);
        rg_main = view.findViewById(R.id.rg_main);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        rg_main.check(R.id.rb_home);
    }

}
