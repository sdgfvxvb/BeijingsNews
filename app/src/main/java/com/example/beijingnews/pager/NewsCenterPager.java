package com.example.beijingnews.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.beijingnews.activity.MainActivity;
import com.example.beijingnews.base.BasePager;
import com.example.beijingnews.base.MenuDetailBasePage;
import com.example.beijingnews.domain.NewsCenterPagerBean;
import com.example.beijingnews.fragment.LeftmenuFragment;
import com.example.beijingnews.menudetailpager.InteracMenuDetailPager;
import com.example.beijingnews.menudetailpager.NewsMenuDetailPager;
import com.example.beijingnews.menudetailpager.PhotosMenuDetailPager;
import com.example.beijingnews.menudetailpager.TopicMenuDetailPager;
import com.example.beijingnews.utils.Constants;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class NewsCenterPager extends BasePager {

    public static final String TAG = "NEWS_CENTER";
    private List<NewsCenterPagerBean.DataBean> data;
    private ArrayList<MenuDetailBasePage> detailBasePages;

    public NewsCenterPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        tv_title.setText("新闻中心");
        ib_menu.setVisibility(View.VISIBLE);

        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        fl_content.addView(textView);

        textView.setText("新闻中心内容");

        getDataFromNet();
    }

    private void getDataFromNet() {

        RequestParams params = new RequestParams(Constants.NEWS_CENTER_PAGE_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "使用xutils联网成功==" + result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "使用xutils联网失败==" + ex.getMessage());

            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d(TAG, "使用xutils联网取消==" + cex.getMessage());

            }

            @Override
            public void onFinished() {
                Log.d(TAG, "使用xutils联网完成");

            }
        });
    }

    private void processData(String json) {

        NewsCenterPagerBean bean = parsedJson(json);
        data = bean.getData();

        MainActivity mainActivity = (MainActivity) context;
        LeftmenuFragment leftmenuFragment = mainActivity.getLeftmenuFragment();

        detailBasePages = new ArrayList<>();
        detailBasePages.add(new NewsMenuDetailPager(context));
        detailBasePages.add(new TopicMenuDetailPager(context));
        detailBasePages.add(new PhotosMenuDetailPager(context));
        detailBasePages.add(new InteracMenuDetailPager(context));

        leftmenuFragment.setData(data);

    }

    private NewsCenterPagerBean parsedJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, NewsCenterPagerBean.class);
    }

    public void swichPager(int position) {
        tv_title.setText(data.get(position).getTitle());
        fl_content.removeAllViews();

        MenuDetailBasePage detailBasePage = detailBasePages.get(position);
        View rootView = detailBasePage.rootView;
        detailBasePage.initData();

        fl_content.addView(rootView);
    }
}
