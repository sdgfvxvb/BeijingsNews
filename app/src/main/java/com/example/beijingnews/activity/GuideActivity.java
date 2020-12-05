package com.example.beijingnews.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.beijingnews.R;
import com.example.beijingnews.SplashActivity;
import com.example.beijingnews.utils.CacheUtils;
import com.example.beijingnews.utils.DensityUtils;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager viewPager;
    private Button btnStartMain;
    private LinearLayout llPrintGroup;
    private ArrayList<ImageView> imageViews;
    private ImageView iv_red_point;
    private int leftmax;
    private int widthdpi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager = findViewById(R.id.view_page);
        btnStartMain = findViewById(R.id.btn_start_main);
        llPrintGroup = findViewById(R.id.ll_point_group);
        iv_red_point = findViewById(R.id.iv_red_point);

        int[] ids = new int[]{
                R.drawable.guide1,
                R.drawable.guide2,
                R.drawable.guide3
        };

        widthdpi = DensityUtils.dip2px(this, 10);

        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);
            imageViews.add(imageView);

            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthdpi, widthdpi);
            if (i != 0) {
                params.leftMargin = widthdpi;
            }

            point.setLayoutParams(params);
            llPrintGroup.addView(point);
        }

        viewPager.setAdapter(new MyPagerAdapter());

        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int leftMargin = (int) (position * leftmax + positionOffset * leftmax);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
                params.leftMargin = leftMargin;
                iv_red_point.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == imageViews.size() - 1) {
                    btnStartMain.setVisibility(View.VISIBLE);
                } else {
                    btnStartMain.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnStartMain.setOnClickListener(v -> {
            CacheUtils.putBoolean(GuideActivity.this, SplashActivity.START_MAIN, true);
            Intent intent = new Intent(GuideActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            Log.d(GuideActivity.class.getSimpleName(), "onGlobalLayout");
            leftmax = llPrintGroup.getChildAt(1).getLeft() - llPrintGroup.getChildAt(0).getLeft();
        }
    }


    class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }
    }

}