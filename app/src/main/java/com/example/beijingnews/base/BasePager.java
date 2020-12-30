package com.example.beijingnews.base;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.beijingnews.R;
import com.example.beijingnews.activity.MainActivity;

public class BasePager {

    public Context context;
    public View rootView;
    public TextView tv_title;
    public ImageButton ib_menu;
    public FrameLayout fl_content;

    public BasePager(Context context) {
        this.context = context;
        this.rootView = initView();
    }

    private View initView() {
        View view = View.inflate(context, R.layout.base_pager, null);
        tv_title = view.findViewById(R.id.tv_title);
        ib_menu = view.findViewById(R.id.ib_menu);
        fl_content = view.findViewById(R.id.fl_content);
        ib_menu.setOnClickListener(v -> {
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.slidingMenu.toggle();
        });
        return view;
    }

    public void initData() {

    }
}
