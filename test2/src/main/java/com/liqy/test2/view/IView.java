package com.liqy.test2.view;

import com.liqy.test2.BaseView;
import com.liqy.test2.model.Product;
import com.liqy.test2.presenter.SearchPresenter;

import java.util.List;

/**
 * @file FileName
 * optMVP
 *  * liqy
 * Copyright 星期一 YourCompany.
 */
public interface IView extends BaseView<SearchPresenter>{
    void firstData(List<Product> list);
    void moreData(List<Product> list);
    void refreshData(List<Product> list);
}
