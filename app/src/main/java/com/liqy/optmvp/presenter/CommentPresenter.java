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
     *
     * @param activity
     */
    public CommentPresenter(CommentActivity activity) {
        task = new CommentTask();
        view = activity;
    }


    /**
     * 首次加载
     */
    public void getFirstData(){
        getCommentList(0,10,false);
    }

    /**
     * 加载更多数据
     * @param page
     */
    public void getMoreData(int page){
        getCommentList(1,10,false);
    }

    /**
     * 刷新数据
     */
    public void refreshData(){
        getCommentList(0,10,true);
    }


    /**
     * 获取列表
     *
     * @param page
     * @param count
     */
    @Override
    public void getCommentList(final int page, int count, final boolean isRefresh) {

        String url = "http://39.108.3.12:3000/v1/comment?restaurant_id=32&offset=" + page + "&limit=" + count;

        task.getCommentList(url, new ICallBack() {
            @Override
            public void success(List<Comment> data) {
                if (view != null) {
                    if (page == 0) {
                        if (isRefresh) {
                            view.showRefreshData(data);
                        } else {
                            view.showFirstData(data);
                        }
                    } else {
                        view.showMoreData(data);
                    }
                }
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
