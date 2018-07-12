package com.liqy.optmvp;

import android.app.Application;
import android.content.Context;

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
    }
}
