package com.liqy.optmvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.liqy.optmvp.R;
import com.liqy.optmvp.model.Comment;
import com.liqy.optmvp.presenter.CommentPresenter;
import com.liqy.optmvp.view.adapter.CommentAdapter;

import java.util.List;

public class CommentActivity extends AppCompatActivity implements ICommentView {

    CommentPresenter presenter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lv_comment);


        setPresenter(new CommentPresenter(this));
        presenter.getCommentList();
    }

    @Override
    public void showData(List<Comment> list) {
        //TODO 处理列表

        CommentAdapter adapter=new CommentAdapter(this);
        adapter.addDatas(list);
        listView.setAdapter(adapter);
    }

    /**
     * 注入P
     *
     * @param presenter
     */
    @Override
    public void setPresenter(CommentPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 网络请求错误的时候执行
     *
     * @param string
     */
    @Override
    public void showError(String string) {
        //TODO 弹出错误信息
    }
}
