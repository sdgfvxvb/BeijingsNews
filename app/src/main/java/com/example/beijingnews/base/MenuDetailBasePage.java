package com.example.beijingnews.base;

import android.content.Context;
import android.view.View;

public abstract class MenuDetailBasePage {


    public Context context;

    public View rootView;

    public MenuDetailBasePage(Context context) {
        this.context = context;
        rootView = initView();
    }

    public abstract View initView();

    public void initData() {

    }
}
