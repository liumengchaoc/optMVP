package com.liqy.test2.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqy.test2.R;
import com.liqy.test2.model.Product;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.List;

/**
 * @file FileName
 * optMVP
 *  * liqy
 * Copyright 星期一 YourCompany.
 */
public class SearchAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater inflater;
    private List<Product> list;

    private DisplayImageOptions options;
    ImageLoader imageLoader;

    public SearchAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
        this.inflater=LayoutInflater.from(context);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher_round) // resource or drawable
                .showImageForEmptyUri(R.mipmap.ic_launcher_round) // resource or drawable
                .showImageOnFail(R.mipmap.ic_launcher_round) // resource or drawable
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.RGB_565) // default
                .displayer(new CircleBitmapDisplayer(Color.BLUE)) // default
                .build();

        imageLoader=ImageLoader.getInstance();
    }

    /**
     * 怎加列表数据
     * @param products
     */
    public void addData(List<Product> products){
        list.addAll(products);
        notifyDataSetChanged();
    }

    public void clearData(){
        list.clear();
    }

    @Override
    public int getCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }

    @Override
    public Product getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_layout,viewGroup,false);
            holder.imageView=(ImageView)view.findViewById(R.id.img_product);
            holder.textView=(TextView)view.findViewById(R.id.txt_title);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }

        Product product=getItem(i);
        //TODO 解析数据之后在处理

        String url= product.images.split("\\|")[0];

        Log.d("URL",url);
        imageLoader.displayImage(url,holder.imageView,options);
        holder.textView.setText(product.title);

        return view;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
