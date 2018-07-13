package com.liqy.optmvp.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.liqy.optmvp.R;
import com.liqy.optmvp.model.Comment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.previewlibrary.GPreviewBuilder;

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
    DisplayImageOptions optionsCircle;
    DisplayImageOptions options;

    public CommentAdapter(Context context) {
        this.comments = new ArrayList<>();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        loader = ImageLoader.getInstance();
        optionsCircle = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher_round) // resource or drawable
                .showImageForEmptyUri(R.mipmap.ic_launcher_round) // resource or drawable
                .showImageOnFail(R.mipmap.ic_launcher_round) // resource or drawable
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.RGB_565) // default
                .displayer(new CircleBitmapDisplayer(Color.BLACK, 1)) // default
                .build();

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher_round) // resource or drawable
                .showImageForEmptyUri(R.mipmap.ic_launcher_round) // resource or drawable
                .showImageOnFail(R.mipmap.ic_launcher_round) // resource or drawable
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.RGB_565) // default
                .displayer(new SimpleBitmapDisplayer()) // default
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
        ViewHolderText holderText = null;
        ViewHolderTextImage holderTextImage = null;

        int type = getItemViewType(i);
        final Comment comment = getItem(i);

        if (view == null) {
            if (type == TYPE_TEXT) {
                view = inflater.inflate(R.layout.item_text, viewGroup, false);
                holderText = new ViewHolderText(view);
                view.setTag(holderText);
            } else if (type == TYPE_TEXT_IMAGE) {
                view = inflater.inflate(R.layout.item_text_image, viewGroup, false);
                holderTextImage = new ViewHolderTextImage(view);
                view.setTag(holderTextImage);
            }
        } else {
            if (type == TYPE_TEXT) {
                holderText = (ViewHolderText) view.getTag();
            } else if (type == TYPE_TEXT_IMAGE) {
                holderTextImage = (ViewHolderTextImage) view.getTag();
            }
        }


        if (type == TYPE_TEXT) {
            holderText.user_name.setText(comment.user_name);
            holderText.user_comment.setText(comment.comment_data);
            holderText.user_comment_time.setText(comment.comment_time);
            loader.displayImage(comment.avatar, holderText.user_icon, optionsCircle);
        } else if (type == TYPE_TEXT_IMAGE) {
            holderTextImage.user_name.setText(comment.user_name);
            holderTextImage.user_comment.setText(comment.comment_data);
            holderTextImage.user_comment_time.setText(comment.comment_time);


            final NineGridImageView nineGridImageView=holderTextImage.user_images;
            NineGridImageViewAdapter<String> adapter = new NineGridImageViewAdapter<String>() {
                @Override
                protected void onDisplayImage(Context context, ImageView imageView, String s) {
                    loader.displayImage(s, imageView, options);
                }

                @Override
                protected void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
                    super.onItemImageClick(context, imageView, index, list);

                    List<PhotoInfo> infos = new ArrayList<>();
                    for (String url : comment.pic_url) {
                        infos.add(new PhotoInfo(url));
                    }

                    /**
                     * 拼接集合
                     */
                    computeBoundsBackward(nineGridImageView,infos);

                    /**
                     * 跳转
                     */
                    GPreviewBuilder.from((Activity) context)
                            .setData(infos)
                            .setCurrentIndex(index)//
                            .setType(GPreviewBuilder.IndicatorType.Number)
                            .start();//启动
                }
            };

            holderTextImage.user_images.setAdapter(adapter);
            holderTextImage.user_images.setImagesData(comment.pic_url);
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
            user_name = (TextView) view.findViewById(R.id.user_name);
            user_comment = (TextView) view.findViewById(R.id.user_comment);
            user_comment_time = (TextView) view.findViewById(R.id.user_comment_time);
            user_icon = (ImageView) view.findViewById(R.id.user_icon);
        }
    }

    /**
     * 图片加文字
     */
    static class ViewHolderTextImage extends ViewHolderText {
        NineGridImageView user_images;

        public ViewHolderTextImage(View view) {
            super(view);
            this.user_images = (NineGridImageView) view.findViewById(R.id.user_images);
        }
    }

    /**
     * 查找信息
     * @param user_images 九宫格控件
     * @param list 数据列表
     */
    private void computeBoundsBackward(NineGridImageView user_images, List<PhotoInfo> list) {
        for (int i = 0; i < user_images.getChildCount(); i++) {
            View itemView = user_images.getChildAt(i);//
            Rect bounds = new Rect();
            if (itemView != null) {
                ImageView thumbView = (ImageView) itemView;
                thumbView.getGlobalVisibleRect(bounds);
            }
            list.get(i).mBounds = bounds;
        }

    }
}
