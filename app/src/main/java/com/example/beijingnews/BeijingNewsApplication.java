package com.example.beijingnews;

import android.app.Application;

import org.xutils.x;

public class BeijingNewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.setDebug(true);
        x.Ext.init(this);
    }
}
