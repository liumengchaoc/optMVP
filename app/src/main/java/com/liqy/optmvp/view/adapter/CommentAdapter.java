package com.liqy.optmvp.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqy.optmvp.R;
import com.liqy.optmvp.model.Comment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

/**
 * @file FileName
 * 评论列表适配器
 * Copyright 星期五 YourCompany.
 */
public class CommentAdapter extends BaseAdapter {

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_TEXT_IMAGE = 1;
    public static final int TYPE_COUNT = 2;

    private List<Comment> comments;

    private Context context;

    private LayoutInflater inflater;
    private ImageLoader loader;
    DisplayImageOptions options;

    public CommentAdapter(Context context) {
        this.comments = new ArrayList<>();
        this.context = context;
        this.inflater=LayoutInflater.from(context);
        loader=ImageLoader.getInstance();
         options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher_round) // resource or drawable
                .showImageForEmptyUri(R.mipmap.ic_launcher_round) // resource or drawable
                .showImageOnFail(R.mipmap.ic_launcher_round) // resource or drawable
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
		        .displayer(new CircleBitmapDisplayer(Color.WHITE)) // default
                .build();
    }

    /**
     * 添加数据
     *
     * @param list
     */
    public void addDatas(List<Comment> list) {
        this.comments.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comment getItem(int i) {
        return comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        Comment comment = getItem(position);
        if (comment.pic_url != null && !comment.pic_url.isEmpty()) {
            return TYPE_TEXT_IMAGE;
        } else {
            return TYPE_TEXT;
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderText holderText=null;
        ViewHolderTextImage holderTextImage=null;

        int type = getItemViewType(i);
        Comment comment=getItem(i);

        if (view == null) {
            if (type == TYPE_TEXT) {
                view=inflater.inflate(R.layout.item_text,viewGroup,false);
                holderText=new ViewHolderText(view);
                view.setTag(holderText);
            } else if (type == TYPE_TEXT_IMAGE) {
                view=inflater.inflate(R.layout.item_text_image,viewGroup,false);
                holderTextImage=new ViewHolderTextImage(view);
                view.setTag(holderTextImage);
            }
        } else {
            if (type == TYPE_TEXT) {
                holderText=(ViewHolderText) view.getTag();
            } else if (type == TYPE_TEXT_IMAGE) {
                holderTextImage=(ViewHolderTextImage)view.getTag();
            }
        }


        if (type == TYPE_TEXT) {
            holderText.user_name.setText(comment.user_name);
            holderText.user_comment.setText(comment.comment_data);
            holderText.user_comment_time.setText(comment.comment_time);
            loader.displayImage(comment.avatar,holderText.user_icon,options);
        } else if (type == TYPE_TEXT_IMAGE) {
            holderTextImage.user_name.setText(comment.user_name);
            holderTextImage.user_comment.setText(comment.comment_data);
            holderTextImage.user_comment_time.setText(comment.comment_time);
        }

        return view;
    }

    /**
     * 只是文字
     */
    static class ViewHolderText {
        TextView user_name;
        TextView user_comment;
        TextView user_comment_time;
        ImageView user_icon;

        public ViewHolderText(View view) {
            user_name=(TextView)view.findViewById(R.id.user_name);
            user_comment=(TextView)view.findViewById(R.id.user_comment);
            user_comment_time=(TextView)view.findViewById(R.id.user_comment_time);
            user_icon=(ImageView) view.findViewById(R.id.user_icon);
        }
    }

    /**
     * 图片加文字
     */
    static class ViewHolderTextImage extends ViewHolderText {
        ImageView user_images;

        public ViewHolderTextImage(View view) {
            super(view);
            this.user_images =(ImageView)view.findViewById(R.id.user_images);
        }
    }


}
