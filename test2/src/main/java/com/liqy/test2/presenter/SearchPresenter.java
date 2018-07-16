package com.liqy.test2.presenter;

import android.util.Log;

import com.liqy.test2.MainActivity;
import com.liqy.test2.model.ICallBack;
import com.liqy.test2.model.RootData;
import com.liqy.test2.model.SearchTask;
import com.liqy.test2.view.IView;

import java.net.URLEncoder;

/**
 * @file FileName
 * optMVP
 *  * liqy
 * Copyright 星期一 YourCompany.
 */
public class SearchPresenter implements IPresenter {

    SearchTask task;
    IView view;

    public SearchPresenter(MainActivity activity) {
        task = new SearchTask();
        this.view = activity;
    }

    @Override
    public void getList(String keyword, final int page, final boolean isRefresh) {
        String url = "https://www.zhaoapi.cn/product/searchProducts?keywords=" + URLEncoder.encode(keyword) + "&page=" + page;
        task.task(url, new ICallBack() {
            @Override
            public void success(RootData data) {
                if (page == 1) {
                    if (isRefresh) {
                        view.refreshData(data.data);
                    } else {
                        view.firstData(data.data);
                    }
                } else {
                    view.moreData(data.data);
                }

            }

            @Override
            public void failed(String msg) {

                Log.d(getClass().getName(),msg);
            }
        });

    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
