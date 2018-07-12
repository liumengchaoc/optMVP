package com.liqy.optmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.liqy.optmvp.model.Comment;
import com.liqy.optmvp.model.CommentTask;
import com.liqy.optmvp.model.ICallBack;
import com.liqy.optmvp.model.ICommentTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ICommentTask task=new CommentTask();
        task.getCommentList("http://39.108.3.12:3000/v1/comment?restaurant_id=32&offset=0&limit=5", new ICallBack() {
            @Override
            public void success(List<Comment> data) {
                Log.d(getLocalClassName(),data.toString());
            }

            @Override
            public void failed(String msg) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
