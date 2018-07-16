package com.liqy.test2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.liqy.test2.model.Product;
import com.liqy.test2.presenter.SearchPresenter;
import com.liqy.test2.view.IView;
import com.liqy.test2.view.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView {

    PullToRefreshListView pull_to_refresh_listview;
    SearchAdapter adapter;
    int page = 1;

    SearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setP(new SearchPresenter(this));
        presenter.getList("笔记本", page, false);

    }

    private void initView() {
        pull_to_refresh_listview = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_listview);
        pull_to_refresh_listview.setMode(PullToRefreshBase.Mode.BOTH);
        pull_to_refresh_listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            /**
             * 下拉刷新
             * @param pullToRefreshBase
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page = 1;
                presenter.getList("笔记本", page, true);

            }

            /**
             * 加载更所
             * @param pullToRefreshBase
             */
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page++;
                presenter.getList("笔记本", page, false);
            }
        });

        adapter = new SearchAdapter(this, new ArrayList<Product>());
        pull_to_refresh_listview.setAdapter(adapter);
    }


    @Override
    public void firstData(List<Product> list) {
        adapter.addData(list);
        pull_to_refresh_listview.onRefreshComplete();
    }

    @Override
    public void moreData(List<Product> list) {
        adapter.addData(list);
        pull_to_refresh_listview.onRefreshComplete();
    }

    @Override
    public void refreshData(List<Product> list) {
        adapter.clearData();
        adapter.addData(list);
        pull_to_refresh_listview.onRefreshComplete();
    }

    @Override
    public void setP(SearchPresenter p) {
        this.presenter = p;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
