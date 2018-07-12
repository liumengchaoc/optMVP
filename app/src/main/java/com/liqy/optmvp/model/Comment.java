package com.liqy.optmvp.model;

import java.util.List;

/**
 * @file FileName
 * optMVP
 *  * liqy
 * Copyright 星期四 YourCompany.
 */
public class Comment {
    public int user_id;
    public int id;
    public String user_name;
    public String avatar;
    public int restaurant_id;
    public String restaurant;
    public String comment_data;
    public int order_id;
    public int food_score;
    public int delivery_score;
    public String comment_time;
    public int __v;
    public List<String> pic_url;

    @Override
    public String toString() {
        return "Comment{" +
                "user_id=" + user_id +
                ", id=" + id +
                ", user_name='" + user_name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", restaurant_id=" + restaurant_id +
                ", restaurant='" + restaurant + '\'' +
                ", comment_data='" + comment_data + '\'' +
                ", order_id=" + order_id +
                ", food_score=" + food_score +
                ", delivery_score=" + delivery_score +
                ", comment_time='" + comment_time + '\'' +
                ", __v=" + __v +
                ", pic_url=" + pic_url +
                '}';
    }
}
