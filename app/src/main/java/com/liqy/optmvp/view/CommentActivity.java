package com.liqy.optmvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.liqy.optmvp.R;
import com.liqy.optmvp.model.Comment;
import com.liqy.optmvp.presenter.CommentPresenter;
import com.liqy.optmvp.view.adapter.CommentAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

public class CommentActivity extends AppCompatActivity implements ICommentView {

    CommentPresenter presenter;
    ListView listView;

    CommentAdapter adapter=new CommentAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv_comment);
        setPresenter(new CommentPresenter(this));

        presenter.getFirstData();//首次加载

        /**
         * 在XListView中的加载更多监听中调用
         */
        presenter.getMoreData(1);//加载更多

        /**
         * 在XListView中的刷新监听中调用
         */
        presenter.refreshData();//刷新数据
    }

    /**
     * 首次加载
     * @param list
     */
    @Override
    public void showFirstData(List<Comment> list) {
        //TODO 处理列表
        Logger.d(list);
        adapter.addDatas(list);
        listView.setAdapter(adapter);
    }

    /**
     * 加载更过
     * @param list
     */
    @Override
    public void showMoreData(List<Comment> list) {
        adapter.addDatas(list);
        listView.setAdapter(adapter);
    }

    /**
     * 下拉刷新
     * @param list
     */
    @Override
    public void showRefreshData(List<Comment> list) {
        adapter.clearData();//清除原有数据
        //添加数据
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
