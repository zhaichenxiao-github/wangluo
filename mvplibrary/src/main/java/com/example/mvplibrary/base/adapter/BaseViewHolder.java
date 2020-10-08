package com.example.mvplibrary.base.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvplibrary.R;
import com.example.mvplibrary.utils.ImageLoad;
import com.example.mvplibrary.view.BaseView;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private Context context;
    private View itemRootView;
    private SparseArray sparseArray=new SparseArray<>();
    public BaseViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.itemRootView=itemView;
        this.context=context;
    }
    //获取控件id
    public <T extends View> T getView(int viewId){
        //判断数组是否为空、为空添加控件id进去、不为空直接返回控件id
        if (sparseArray.get(viewId)==null){
            View viewById = itemRootView.findViewById(viewId);
            sparseArray.put(viewId, viewById);
        }
        return (T) sparseArray.get(viewId);
    }

    public BaseViewHolder setTextContent(int textViewId,String text,OnItemClickListener onItemClickListener){
        TextView textView = getView(textViewId);
        if (onItemClickListener!=null){
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(text);
                }
            });
        }
        textView.setText(text);
        return this;
    }
    public BaseViewHolder setTextContent(int textViewId,String text){
        TextView textView = getView(textViewId);
        textView.setText(text);
        return this;
    }
    public BaseViewHolder setImageContent(int textViewId,String path){
        ImageView imageView = getView(textViewId);
        ImageLoad imageLoad = new ImageLoad(context);
        imageLoad.loadImage(imageView, path);
        return this;
    }
    public BaseViewHolder setImageContent(int textViewId,String path,OnItemClickListener onItemClickListener){
        ImageView imageView = getView(textViewId);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(path);
            }
        });
        ImageLoad imageLoad = new ImageLoad(context);
        imageLoad.loadImage(imageView, path);
        return this;
    }

    interface OnItemClickListener{
        void onItemClick(String itemString);
    }

}
