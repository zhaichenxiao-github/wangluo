package com.example.wangluo.mvp;

import com.example.httplibrary.SpUtils;
import com.example.httplibrary.client.HttpClient;
import com.example.httplibrary.utils.JsonUtils;
import com.example.wangluo.app.ShopApplication;
import com.example.wangluo.bean.CartBean;
import com.example.wangluo.bean.CateGoryCallback;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartModelIml implements CartConstacts.CartModel{
    @Override
    public void OnSuccessCart(ModelCartBack<List<CartBean>> back, LifecycleProvider lifecycleProvider) {
        Map<String,Object> map=new HashMap<>();
        map.put("token", SpUtils.get(ShopApplication.getApp(), "share_data","token",0));
        new HttpClient.Builder()
                .setApiUrl("cart/getList")
                .post()
                .setJsonBody("{}", true)
                .setHeadres(map)
                .build().request(new CateGoryCallback<List<CartBean>>() {
            @Override
            public void onSuccess(List<CartBean> list) {
                back.OnSuccessCart(list);
            }

            @Override
            public List<CartBean> convert(JsonElement result) {
                return JsonUtils.jsonToClassList(result, CartBean.class);
            }

            @Override
            public void onError(String message, int code) {

            }

            @Override
            public void cancle() {

            }
        });
    }
}
