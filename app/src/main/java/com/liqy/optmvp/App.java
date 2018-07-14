package com.liqy.optmvp;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import com.liqy.optmvp.view.adapter.TestImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.previewlibrary.ZoomMediaLoader;

/**
 * @file FileName
 * optMVP
 *  * liqy
 * Copyright 星期四 YourCompany.
 */
public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        //日志库初始化
        Logger.addLogAdapter(new AndroidLogAdapter(){
                 @Override
                 public boolean isLoggable(int priority, @Nullable String tag) {
                     //todo 关闭日志 返回 false
                    return super.isLoggable(priority, tag);
                   }
                 }
        );


        //大图浏览初始化
        ZoomMediaLoader.getInstance().init(new TestImageLoader());

        //图片加载库初始化
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
