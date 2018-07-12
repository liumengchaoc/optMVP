package com.liqy.optmvp.contract;

import com.liqy.optmvp.BasePresenter;
import com.liqy.optmvp.BaseView;
import com.liqy.optmvp.model.Comment;
import com.liqy.optmvp.presenter.CommentPresenter;

import java.util.List;

/**
 * @file FileName
 *一个合约
 * Copyright 星期四 YourCompany.
 */
public interface CommentContract {

    public interface ICommentPresenter extends BasePresenter {
        void getCommentList();
    }

    public interface ICommentView extends BaseView<CommentPresenter> {
        /**
         * 展示数据
         * @param list
         */
        void showData(List<Comment> list);

        void showError(String string);
    }

}
