package com.liqy.test2;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * @file FileName
 * optMVP
 *  * liqy
 * Copyright 星期一 YourCompany.
 */
public class App extends Application{

   public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        context=this;
        //图片加载库初始化
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
