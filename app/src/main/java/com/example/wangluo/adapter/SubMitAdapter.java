package com.example.wangluo.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wangluo.R;
import com.example.wangluo.bean.CartBean;

import java.util.List;

public class SubMitAdapter extends BaseQuickAdapter<CartBean, BaseViewHolder> {
    private Context context;
    public SubMitAdapter(Context context,int layoutResId, @Nullable List<CartBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CartBean item) {
        ImageView iv_goodsDefaultIcon = helper.getView(R.id.iv_goodsDefaultIcon);
        TextView tv_goodsDesc = helper.getView(R.id.tv_goodsDesc);
        TextView tv_goodsDefaultSku = helper.getView(R.id.tv_goodsDefaultSku);
        TextView tv_goodsDefaultPrice = helper.getView(R.id.tv_goodsDefaultPrice);
        TextView tv_count = helper.getView(R.id.tv_count);
        Glide.with(context).load(item.getGoodsIcon()).into(iv_goodsDefaultIcon);
        tv_goodsDesc.setText(item.getGoodsDesc());
        tv_goodsDefaultPrice.setText(item.getGoodsPrice());
        tv_goodsDefaultSku.setText(item.getGoodsSku());
        tv_count.setText(item.getGoodsCount()+"");
    }
}
