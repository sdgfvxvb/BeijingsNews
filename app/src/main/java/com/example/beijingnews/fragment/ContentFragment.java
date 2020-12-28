package com.example.beijingnews.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.beijingnews.R;
import com.example.beijingnews.base.BaseFragment;
import com.example.beijingnews.base.BasePager;
import com.example.beijingnews.pager.GovaffairPager;
import com.example.beijingnews.pager.HomePager;
import com.example.beijingnews.pager.NewsCenterPager;
import com.example.beijingnews.pager.SettingPager;
import com.example.beijingnews.pager.SmartServicePager;
import com.example.beijingnews.view.NoScrollViewPager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.view_page)
    private NoScrollViewPager viewPager;
    @ViewInject(R.id.rg_main)
    private RadioGroup rg_main;

    private ArrayList<BasePager> basePagers;

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.content_fragment, null);
        x.view().inject(ContentFragment.this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(context));
        basePagers.add(new NewsCenterPager(context));
        basePagers.add(new SmartServicePager(context));
        basePagers.add(new GovaffairPager(context));
        basePagers.add(new SettingPager(context));

        viewPager.setAdapter(new ContentFragmentAdapter());

        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        viewPager.addOnPageChangeListener(new MyOnPageChageListener());

        rg_main.check(R.id.rb_home);
        basePagers.get(0).initData();
    }

    class MyOnPageChageListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            basePagers.get(position).initData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_home:
                    viewPager.setCurrentItem(0, false);
                    break;
                case R.id.rb_news_center:
                    viewPager.setCurrentItem(1, false);
                    break;
                case R.id.rb_smart_service:
                    viewPager.setCurrentItem(2, false);
                    break;
                case R.id.rb_govaffair:
                    viewPager.setCurrentItem(3, false);
                    break;
                case R.id.rb_setting:
                    viewPager.setCurrentItem(4, false);
                    break;
            }
        }
    }

    class ContentFragmentAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return basePagers.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            BasePager basePager = basePagers.get(position);
            basePager.initData();
            View rootView = basePager.rootView;
            container.addView(rootView);
            return rootView;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }


}
