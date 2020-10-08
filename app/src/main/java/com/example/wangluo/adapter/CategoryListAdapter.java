package com.example.wangluo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wangluo.R;
import com.example.wangluo.bean.CateGoryListBean;

import java.util.List;

public class CategoryListAdapter extends BaseQuickAdapter<CateGoryListBean,BaseViewHolder> {
    private Context context;

    public CategoryListAdapter(Context context,int layoutResId, @Nullable List<CateGoryListBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CateGoryListBean item) {
        helper.setText(R.id.tv_text, item.getCategoryName());
        ImageView iv = helper.getView(R.id.iv_img);
        Glide.with(context).load(item.getCategoryIcon()).into(iv);
        int layoutPosition = helper.getLayoutPosition();
        helper.addOnClickListener(layoutPosition);
    }
}
