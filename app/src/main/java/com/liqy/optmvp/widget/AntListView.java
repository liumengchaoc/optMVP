package com.liqy.optmvp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @file FileName
 * 完美解决ScrollView嵌套ListView
 * 页面里有ListView（GridView）和一些固定布局让他们一起在整个屏幕上滚动方
 * Copyright 星期五 YourCompany.
 */
public class AntListView extends ListView{
    public AntListView(Context context) {
        super(context);
    }

    public AntListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);    }
}
