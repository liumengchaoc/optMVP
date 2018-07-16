package com.liqy.test2.presenter;

import android.widget.BaseAdapter;

import com.liqy.test2.BasePresenter;

/**
 * @file FileName
 * optMVP
 *  * liqy
 * Copyright 星期一 YourCompany.
 */
public interface IPresenter extends BasePresenter{
   void getList(String keyword,int page,boolean isRefresh);
}
