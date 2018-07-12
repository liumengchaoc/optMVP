package com.liqy.optmvp.model;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.liqy.optmvp.App;

import java.lang.reflect.Type;

import ikidou.reflect.TypeBuilder;

/**
 * @file FileName
 * optMVP
 * liqy
 * Copyright 星期四 YourCompany.
 */
public class CommentTask implements ICommentTask {
    @Override
    public void getCommentList(String url, final ICallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(App.context);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                RootData<Comment> data = fromJsonArray(response, Comment.class);
                callBack.success(data.data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.failed(error.getMessage());
            }
        });

        queue.add(request);

    }

    /**
     * 解析泛型类
     * @param reader
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> RootData<T> fromJsonArray(String reader, Class<T> clazz) {
        Type type = TypeBuilder
                .newInstance(RootData.class)
                .addTypeParam(clazz)
                .build();
        return new Gson().fromJson(reader, type);
    }


}
