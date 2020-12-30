package com.example.beijingnews.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.beijingnews.R;
import com.example.beijingnews.activity.MainActivity;
import com.example.beijingnews.base.BaseFragment;
import com.example.beijingnews.domain.NewsCenterPagerBean;
import com.example.beijingnews.utils.DensityUtils;

import java.util.List;

public class LeftmenuFragment extends BaseFragment {

    private ListView listView;
    private List<NewsCenterPagerBean.DataBean> data;
    private LeftmenuFragmentAdapter adapter;
    private int prePosition;

    @Override
    public View initView() {
        listView = new ListView(context);
        listView.setPadding(1, DensityUtils.dip2px(context, 40), 0, 0);
        listView.setDividerHeight(0);
        listView.setCacheColorHint(Color.TRANSPARENT);
        listView.setSelector(R.color.transparent);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prePosition = position;
                adapter.notifyDataSetChanged();

                MainActivity mainActivity = (MainActivity) context;
                mainActivity.slidingMenu.toggle();


            }
        });

        return listView;
    }

    @Override
    public void initData() {
        super.initData();

    }

    public void setData(List<NewsCenterPagerBean.DataBean> data) {
        this.data = data;
        for (int i = 0; i < data.size(); i++) {

        }
        adapter = new LeftmenuFragmentAdapter();
        listView.setAdapter(adapter);
    }

    class LeftmenuFragmentAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(context, R.layout.item_leftmenu, null);
            textView.setText(data.get(position).getTitle());
            textView.setEnabled(position == prePosition);
            return textView;
        }
    }
}
