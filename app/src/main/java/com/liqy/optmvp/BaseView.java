package com.liqy.optmvp;

/**
 * @file FileName
 * optMVP
 *  * liqy
 * Copyright 星期四 YourCompany.
 */
public interface BaseView<T> {
    /**
     *P注入V层
     * @param presenter
     */
    void setPresenter(T presenter);
}
