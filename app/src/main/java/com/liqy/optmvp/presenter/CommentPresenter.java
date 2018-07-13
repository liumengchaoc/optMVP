package com.liqy.optmvp.presenter;

import com.liqy.optmvp.model.Comment;
import com.liqy.optmvp.model.CommentTask;
import com.liqy.optmvp.model.ICallBack;
import com.liqy.optmvp.view.CommentActivity;
import com.liqy.optmvp.view.ICommentView;

import java.util.List;

/**
 * @file FileName
 * optMVP
 *  * liqy
 * Copyright 星期四 YourCompany.
 */
public class CommentPresenter implements ICommentPresenter {


    CommentTask task;

    ICommentView view;

    /**
     * 构造方法
     * @param activity
     */
    public CommentPresenter(CommentActivity activity) {
        task = new CommentTask();
        view = activity;
    }

    /**
     * 获取评论列表
     */
    @Override
    public void getCommentList() {
        task.getCommentList("http://39.108.3.12:3000/v1/comment?restaurant_id=32&offset=0&limit=5", new ICallBack() {
            @Override
            public void success(List<Comment> data) {
                if (view != null)
                    view.showData(data);
            }

            @Override
            public void failed(String msg) {
                if (view != null)
                    view.showError(msg);
            }
        });
    }

    @Override
    public void start() {

    }

    /**
     * 销毁，防止内存泄漏
     */
    @Override
    public void onDestroy() {
        view = null;
    }
}
