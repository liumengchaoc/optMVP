package com.liqy.optmvp.presenter;

import com.liqy.optmvp.BasePresenter;

/**
 * @file FileName
 * optMVP
 *  * liqy
 * Copyright 星期四 YourCompany.
 */
public interface ICommentPresenter extends BasePresenter {
    void getCommentList(int page,int count,boolean isRefresh);
}
