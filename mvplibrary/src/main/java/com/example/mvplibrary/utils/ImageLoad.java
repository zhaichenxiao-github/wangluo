package com.example.mvplibrary.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageLoad {
    private Context context;

    public ImageLoad(Context context) {
        this.context = context;
    }
    public void loadImage(ImageView imageView,String path){
        Glide.with(context).load(path).into(imageView);
    }
}
