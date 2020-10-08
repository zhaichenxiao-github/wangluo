package com.example.wangluo.mvp;

import com.example.httplibrary.SpUtils;
import com.example.httplibrary.client.HttpClient;
import com.example.httplibrary.utils.JsonUtils;
import com.example.wangluo.app.ShopApplication;
import com.example.wangluo.bean.CateGoryCallback;
import com.example.wangluo.bean.SubParams;
import com.example.wangluo.bean.SubmitBean;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubmitModelIml implements SubmitConcast.SubModel{
    @Override
    public void OnSuccessSub(SubParams subParams, ModelSubCallBack<List<SubmitBean>> back, LifecycleProvider lifecycleProvider) {
        Map<String,Object> map  = new HashMap<>();
        map.put("token", SpUtils.get(ShopApplication.getApp(),"share_data","token",0));
        new HttpClient.Builder()
                .setJsonBody(JsonUtils.classToJson(subParams),true)
                .post()
                .setApiUrl("shipAddress/getList")
                .setLifecycleProvider(lifecycleProvider)
                .setHeadres(map)
                .build().request(new CateGoryCallback<List<SubmitBean>>() {
            @Override
            public void onSuccess(List<SubmitBean> list) {
                back.OnSuccessSub(list);
            }

            @Override
            public List<SubmitBean> convert(JsonElement result) {
                return JsonUtils.jsonToClassList(result,SubmitBean.class);
            }

            @Override
            public void onError(String message, int code) {
                back.OnError(message,code);
            }

            @Override
            public void cancle() {

            }
        });
    }
}
