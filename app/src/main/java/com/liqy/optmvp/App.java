package com.liqy.optmvp;

import android.app.Application;
import android.content.Context;

import com.liqy.optmvp.view.adapter.TestImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.previewlibrary.ZoomMediaLoader;

/**
 * @file FileName
 * optMVP
 *  * liqy
 * Copyright 星期四 YourCompany.
 */
public class App extends Application{

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

        //大图浏览初始化
        ZoomMediaLoader.getInstance().init(new TestImageLoader());

        //图片加载库初始化
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
