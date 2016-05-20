package com.malus.pushshow.Application;

import android.app.Application;

import com.malus.pushshow.utils.SPHelper;

/********************
 * 作者：malus
 * 日期：16/2/23
 * 时间：下午12:10
 * 注释：
 ********************/
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SPHelper.initSPHelper(this);
    }
}
