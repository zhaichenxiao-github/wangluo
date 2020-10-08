package com.example.wangluo.utils;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import com.example.httplibrary.SpUtils;
import com.example.httplibrary.client.HttpClient;
import com.example.httplibrary.utils.JsonUtils;
import com.example.wangluo.R;
import com.example.wangluo.activity.CartActivity;
import com.example.wangluo.app.ShopApplication;
import com.example.wangluo.bean.AddParmas;
import com.example.wangluo.bean.CateGoryCallback;
import com.example.wangluo.bean.ShopHomeList;
import com.example.wangluo.event.SkuChangedEvent;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonElement;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ren.qinc.numberbutton.NumberButton;

public class SkuDiaLog extends BottomSheetDialog implements View.OnClickListener {
    private ShopHomeList goodsDetailBean;
    private List<ShopHomeList.GoodsSkuBean> goodsSkuBeans;

    private Context context;
    //存储所有Sku的集合
    private List<Map<TagFlowLayout, List<String>>> skuChangedEvents = new ArrayList<>();
    public final NumberButton mNumberButton;
    private AddParmas addParmas;
    private final TextView text_count;

    public SkuDiaLog(@NonNull Context context, ShopHomeList goodsDetailBean) {
        super(context);
        this.context=context;
        this.goodsDetailBean = goodsDetailBean;
        this.goodsSkuBeans = goodsDetailBean.getGoodsSku();
        setContentView(R.layout.layout_pop);
        findViewById(R.id.btn_add_card).setOnClickListener(this);
        findViewById(R.id.mCloseIv).setOnClickListener(this);
        //购物数量
        mNumberButton = findViewById(R.id.mNumberButton);
        text_count = mNumberButton.findViewById(R.id.text_count);
        //初始化默认值
        mNumberButton.setCurrentNumber(1);
        //mNumberButton变化的监听
        text_count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EventBus.getDefault().postSticky(new SkuChangedEvent());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //赋值
        ImageView iv_defaulticon = findViewById(R.id.iv_defaulticon);
        TextView tv_defaultprice = findViewById(R.id.tv_defaultprice);
        TextView tv_goodscode = findViewById(R.id.tv_goodscode);
        Glide.with(context).load(goodsDetailBean.getGoodsDefaultIcon()).into(iv_defaulticon);
        tv_defaultprice.setText("￥" + goodsDetailBean.getGoodsDefaultPrice());
        tv_goodscode.setText("商品编号:" + goodsDetailBean.getGoodsCode());
        //父控件
        LinearLayout linearLayout = findViewById(R.id.mSkuContents);
        View inflate = null;
        for (ShopHomeList.GoodsSkuBean goodsSkuBean : goodsSkuBeans) {

            Map<TagFlowLayout, List<String>> tagFlowLayoutListMap = new HashMap<>();
            //初始化一组数据
            inflate = LayoutInflater.from(context).inflate(R.layout.layout_sku_item, null, false);
            TagFlowLayout tagFlowLayout = inflate.findViewById(R.id.mSkuContentView);
            TextView tv_mSkuTitle = inflate.findViewById(R.id.tv_mSkuTitle);
            tv_mSkuTitle.setText(goodsSkuBean.getGoodsSkuTitle());
            //得到sku数据
            List<String> skuContent = goodsSkuBean.getSkuContent();
            tagFlowLayout.setAdapter(new TagAdapter<String>(skuContent) {

                @Override
                public View getView(FlowLayout parent, int position, String name) {
                    TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.layout_text_sku, parent, false);
                    textView.setText(name);
                    return textView;
                }
            });
            //初始化默认位置
            tagFlowLayout.getAdapter().setSelectedList(0);
            tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    EventBus.getDefault().postSticky(new SkuChangedEvent());
                    return true;
                }
            });
            tagFlowLayoutListMap.put(tagFlowLayout, skuContent);
            skuChangedEvents.add(tagFlowLayoutListMap);
            //将第一组数据添加到父控件中
            linearLayout.addView(inflate);
        }
    }

    ///获取全部选中的sku
    public List<String> getSkuChangedEvents() {
        List<String> checkSku = new ArrayList<>();
        for (Map<TagFlowLayout, List<String>> skuChangedEvent : skuChangedEvents) {
            //遍历集合Map
            Set<Map.Entry<TagFlowLayout, List<String>>> entries = skuChangedEvent.entrySet();
            for (Map.Entry<TagFlowLayout, List<String>> entry : entries) {
                TagFlowLayout tagFlowLayout = entry.getKey();
                List<String> value = entry.getValue();
                checkSku.add(value.get(tagFlowLayout.getSelectedList().iterator().next()));
            }
        }
        return checkSku;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mCloseIv:
                dismiss();
                //防止事件重复在dismiss完取消订阅
                if (!EventBus.getDefault().isRegistered(this)) {
                    EventBus.getDefault().unregister(this);
                }
                break;
            case R.id.btn_add_card:
                Object o =  SpUtils.get(ShopApplication.getApp(), "share_data", "token", 0);
                if (o!=null){
                    addParmas = new AddParmas();
                    addParmas.setGoodsSku(goodsDetailBean.getGoodsDefaultSku());
                    addParmas.setGoodsPrice(Integer.parseInt(goodsDetailBean.getGoodsDefaultPrice()));
                    addParmas.setGoodsId(goodsDetailBean.getId());
                    addParmas.setGoodsIcon(goodsDetailBean.getGoodsDefaultIcon());
                    addParmas.setGoodsDesc(goodsDetailBean.getGoodsDesc());
                    addParmas.setGoodsCount(Integer.parseInt(text_count.getText().toString()));
                }
                Log.e("token", "onClick: "+addParmas.toString() );
                Map<String,Object> map=new HashMap<>();
                map.put("token", SpUtils.get(ShopApplication.getApp(), "share_data", "token", 0));
                Log.e("token", "onClick: "+o.toString() );
                new HttpClient.Builder()
                        .setJsonBody(JsonUtils.classToJson(addParmas), true)
                        .post()
                        .setHeadres(map)
                        .setApiUrl("cart/add")
                        .build().request(new CateGoryCallback<Integer>() {
                    @Override
                    public void onSuccess(Integer integer) {
                        Toast.makeText(ShopApplication.getApp(), integer.toString(),Toast.LENGTH_SHORT ).show();
                    }

                    @Override
                    public Integer convert(JsonElement result) {
                        return result.getAsNumber().intValue();
                    }

                    @Override
                    public void onError(String message, int code) {

                    }

                    @Override
                    public void cancle() {

                    }
                });
                Intent intent = new Intent(context, CartActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
