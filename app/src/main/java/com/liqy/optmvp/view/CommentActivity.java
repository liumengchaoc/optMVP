package com.liqy.optmvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liqy.optmvp.R;
import com.liqy.optmvp.contract.CommentContract;
import com.liqy.optmvp.presenter.CommentPresenter;

import java.util.List;

public class CommentActivity extends AppCompatActivity implements ICommentView {

    CommentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        setPresenter(new CommentPresenter(this));

        presenter.getCommentList();
    }

    @Override
    public void showData(List list) {

        //TODO 处理列表
    }

    /**
     * 注入P
     * @param presenter
     */
    @Override
    public void setPresenter(CommentPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 网络请求错误的时候执行
     * @param string
     */
    @Override
    public void showError(String string) {
        //TODO 弹出错误信息
    }
}
