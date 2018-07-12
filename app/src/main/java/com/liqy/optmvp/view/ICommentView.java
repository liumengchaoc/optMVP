package com.liqy.optmvp.view;

import com.liqy.optmvp.BaseView;
import com.liqy.optmvp.model.Comment;
import com.liqy.optmvp.presenter.CommentPresenter;

import java.util.List;

/**
 * @file FileName
 * Copyright 星期四 YourCompany.
 */
public interface ICommentView extends BaseView<CommentPresenter> {
    /**
     * 展示数据
     * @param list
     */
    void showData(List<Comment> list);

    void showError(String string);
}
