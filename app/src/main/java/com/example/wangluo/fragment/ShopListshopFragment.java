package com.example.wangluo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.httplibrary.client.HttpClient;
import com.example.httplibrary.utils.JsonUtils;
import com.example.httplibrary.utils.LogUtils;
import com.example.wangluo.R;
import com.example.wangluo.bean.ShopHomeIdBean;
import com.example.wangluo.bean.ShopHomeList;
import com.example.wangluo.event.SkuChangedEvent;
import com.example.wangluo.httpdemo.wanandroid.HttpCallBack;
import com.example.wangluo.utils.SkuDiaLog;
import com.google.gson.JsonElement;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopListshopFragment extends Fragment implements View.OnClickListener {



    private int id;
    private TextView tv_shop_list_listshop_goodsDesc;
    private TextView tv_shop_list_listshop_goodsDefaultPrice;
    private TextView tv_goodsDefaultSku;

    private Banner banner;
    private SkuDiaLog skuDiaLog;
    private View ll_shop;

    public ShopListshopFragment(int id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_listshop, container, false);
        initView(view);
        initData();
        EventBus.getDefault().register(this);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void changeSku(SkuChangedEvent skuChangedEvent) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < skuDiaLog.getSkuChangedEvents().size(); i++) {
            stringBuilder.append(skuDiaLog.getSkuChangedEvents().get(i));
            if (i != skuDiaLog.getSkuChangedEvents().size() - 1) {
                stringBuilder.append(",");
            }
        }
        tv_goodsDefaultSku.setText(stringBuilder.toString() + skuDiaLog.mNumberButton.getNumber() + "件");
    }

    private void initData() {
        ShopHomeIdBean shopHomeIdBean = new ShopHomeIdBean();
        shopHomeIdBean.setGoodsId(id);

        new HttpClient.Builder()
                .setApiUrl("goods/getGoodsDetail")
                .post()
                .setJsonBody(JsonUtils.classToJson(shopHomeIdBean), true)
                .build()
                .request(new HttpCallBack<ShopHomeList>() {

                    @Override
                    public void onError(String msg, int code) {
                        Log.i("sss", msg);
                    }

                    @Override
                    public void cancle() {

                    }

                    @Override
                    public void onSuccess(ShopHomeList goodsDetailBean) {
                        LogUtils.e(goodsDetailBean.toString());
                        String goodsBanner = goodsDetailBean.getGoodsBanner();
                        String[] split = goodsBanner.split(",");
                        ArrayList<String> images = new ArrayList<>();
                        for (int i = 0; i < split.length; i++) {
                            images.add(split[i]);
                        }
                        banner.setImages(images)
                                .setImageLoader(new ImageLoader() {
                                    @Override
                                    public void displayImage(Context context, Object path, ImageView imageView) {
                                        Glide.with(context).load(path).into(imageView);
                                    }
                                }).start();
                        tv_shop_list_listshop_goodsDefaultPrice.setText("￥" + goodsDetailBean.getGoodsDefaultPrice());
                        tv_shop_list_listshop_goodsDesc.setText(goodsDetailBean.getGoodsDesc());
                        tv_goodsDefaultSku.setText(goodsDetailBean.getGoodsDefaultSku());
                        skuDiaLog = new SkuDiaLog(getActivity(), goodsDetailBean);
                    }

                    @Override
                    public ShopHomeList convert(JsonElement result) {
                        return JsonUtils.jsonToClass(result, ShopHomeList.class);
                    }
                });
    }

    private void initView(View view) {
        banner = view.findViewById(R.id.banner);
        tv_shop_list_listshop_goodsDesc = view.findViewById(R.id.tv_shop_list_listshop_goodsDesc);
        tv_goodsDefaultSku = view.findViewById(R.id.tv_goodsDefaultSku);
        tv_shop_list_listshop_goodsDefaultPrice = view.findViewById(R.id.tv_shop_list_listshop_goodsDefaultPrice);
        ll_shop = view.findViewById(R.id.ll_shop);
       ll_shop.setOnClickListener(this);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        skuDiaLog.show();
    }
}