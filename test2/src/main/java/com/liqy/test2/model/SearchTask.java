package com.liqy.test2.model;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.liqy.test2.App;

/**
 * @file FileName
 * optMVP
 *  * liqy
 * Copyright 星期一 YourCompany.
 */
public class SearchTask implements ITask{

    @Override
    public void task(String url, final ICallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(App.context);
        final StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                RootData rootData = gson.fromJson(response, RootData.class);
                callBack.success(rootData);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.failed(error.getMessage());
            }
        });

        queue.add(request);
    }

}
