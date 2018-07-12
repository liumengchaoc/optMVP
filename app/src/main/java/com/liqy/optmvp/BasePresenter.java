package com.liqy.optmvp;

/**
 * @file FileName
 * 防止内存泄漏
 * Copyright 星期四 YourCompany.
 */
public interface BasePresenter {

    /**
     * 开始执行
     */
    void start();

    /**
     * 销毁正在的操作，防止内存泄漏
     */
    void onDestroy();
}
