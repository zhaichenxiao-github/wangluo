package com.example.wangluo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wangluo.R;
import com.example.wangluo.bean.ShopBean;

import java.util.List;

public class ShopListAdapter extends BaseQuickAdapter<ShopBean, BaseViewHolder> {
    private Context context;
    public ShopListAdapter(Context context,int layoutResId, @Nullable List<ShopBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ShopBean item) {
        ImageView iv_shop_list = helper.getView(R.id.iv_shop_list);
        TextView tv_goodsDesc = helper.getView(R.id.tv_goodsDesc);
        TextView tv_goodsDefaultPrice = helper.getView(R.id.tv_goodsDefaultPrice);
        TextView tv_goodsSalesCount = helper.getView(R.id.tv_goodsSalesCount);
        TextView tv_goodsStockCount = helper.getView(R.id.tv_goodsStockCount);
        Glide.with(context).load(item.getGoodsDefaultIcon()).into(iv_shop_list);
        tv_goodsDesc.setText(item.getGoodsDesc());
        tv_goodsDefaultPrice.setText("￥:"+item.getGoodsDefaultPrice());
        tv_goodsSalesCount.setText("销量"+item.getGoodsSalesCount()+"件");
        tv_goodsStockCount.setText("库存"+item.getGoodsStockCount());
        int position = helper.getLayoutPosition();
        helper.addOnClickListener(position);

    }
}
