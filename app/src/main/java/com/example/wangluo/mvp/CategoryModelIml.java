package com.example.wangluo.mvp;

import android.util.Log;

import com.example.httplibrary.SpUtils;
import com.example.httplibrary.callback.BaseCallBack;
import com.example.httplibrary.client.HttpClient;
import com.example.httplibrary.utils.JsonUtils;
import com.example.mvplibrary.model.BaseModelCallBack;
import com.example.wangluo.app.ShopApplication;
import com.example.wangluo.bean.CateGoryCallback;
import com.example.wangluo.bean.CateGoryDemo;
import com.example.wangluo.bean.CateGoryIdBean;
import com.example.wangluo.bean.CateGoryListBean;
import com.example.wangluo.bean.LoginBean;
import com.example.wangluo.bean.LoginIdBean;
import com.example.wangluo.bean.ShopBean;
import com.example.wangluo.bean.ShopHomeIdBean;
import com.example.wangluo.bean.ShopHomeList;
import com.example.wangluo.bean.ShopIdBean;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CategoryModelIml implements CategoryConstacts.CategoryModel {


    @Override
    public void showLogin(LoginIdBean loginIdBean, BaseModelCallBack<List<CateGoryDemo>, List<CateGoryListBean>, List<ShopBean>, ShopHomeList, LoginBean> beanBaseModelCallBack, LifecycleProvider lifecycleProvider) {
        new HttpClient.Builder()
                .setApiUrl("userCenter/login")
                .post().setJsonBody(JsonUtils.classToJson(loginIdBean), true)
                .setLifecycleProvider(lifecycleProvider)
                .build().request(new BaseCallBack<LoginBean>() {

            private int status;
            private JSONObject jsonObject;

            @Override
            public void onError(String message, int code) {
                beanBaseModelCallBack.onFail(message, code);
            }

            @Override
            public void cancle() {

            }

            @Override
            protected LoginBean onConvert(String result) {
                try {
                    jsonObject = new JSONObject(result);
                    status = jsonObject.getInt("status");
                    String message = jsonObject.getString("message");
                    if (status ==0){
                        JSONObject data = jsonObject.getJSONObject("data");
                        LoginBean loginBean = JsonUtils.jsonToClassId(data.toString(), LoginBean.class);
                        SpUtils.put(ShopApplication.getApp(), "share_data", "token", loginBean.getId());
                        Log.e("loginBean", "onConvert: "+loginBean.getId() );
                        return loginBean;
                    }else if (status ==-1){
                        onError(message, status);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public boolean isCodeSuccess() {
                return status==0;
            }

            @Override
            public void onSuccess(LoginBean loginBean) {
                beanBaseModelCallBack.onSuccessLogin(loginBean);
            }

            @Override
            public LoginBean convert(JsonElement result) {
                return null;
            }
            });
    }

    @Override
    public void showShopHomeList(ShopHomeIdBean shopHomeIdBean, BaseModelCallBack<List<CateGoryDemo>, List<CateGoryListBean>, List<ShopBean>, ShopHomeList, LoginBean> baseModelCallBack, LifecycleProvider lifecycleProvider) {
        new HttpClient.Builder()
                .setApiUrl("goods/getGoodsDetail")
                .setLifecycleProvider(lifecycleProvider)
                .setJsonBody(JsonUtils.classToJson(shopHomeIdBean),true )
                .post()
                .build().request(new CateGoryCallback<ShopHomeList>() {
            @Override
            public void onSuccess(ShopHomeList shopHomeList) {
                baseModelCallBack.onSuccessShopHomeList(shopHomeList);

            }

            @Override
            public ShopHomeList convert(JsonElement result) {
                return JsonUtils.jsonToClass(result, ShopHomeList.class);
            }

            @Override
            public void onError(String message, int code) {

            }

            @Override
            public void cancle() {

            }
        });
    }

    @Override
    public void showShopList(ShopIdBean shopIdBean, BaseModelCallBack<List<CateGoryDemo>, List<CateGoryListBean>, List<ShopBean>, ShopHomeList,LoginBean> baseModelCallBack, LifecycleProvider lifecycleProvider) {
        new HttpClient.Builder()
                .setJsonBody(JsonUtils.classToJson(shopIdBean), true)
                .setApiUrl("goods/getGoodsList")
                .setLifecycleProvider(lifecycleProvider)
                .post()
                .build().request(new CateGoryCallback<List<ShopBean>>() {
            @Override
            public void onSuccess(List<ShopBean> shopBeans) {
                baseModelCallBack.onSuccessShopList(shopBeans);
            }

            @Override
            public List<ShopBean> convert(JsonElement result) {
                List<ShopBean> shopBeans = JsonUtils.jsonToClassList(result, ShopBean.class);
                return shopBeans;
            }

            @Override
            public void onError(String message, int code) {

            }

            @Override
            public void cancle() {

            }
        });
    }

    @Override
    public void showCategoryTab(CateGoryIdBean cateGoryIdBean, BaseModelCallBack<List<CateGoryDemo>, List<CateGoryListBean>,List<ShopBean>,ShopHomeList,LoginBean> baseModelCallBack, LifecycleProvider lifecycleProvider) {
        new HttpClient.Builder()
                .setLifecycleProvider(lifecycleProvider)
                .setApiUrl("category/getCategory")
                .setJsonBody(JsonUtils.classToJson(cateGoryIdBean), true)
                .post()
                .build().request(new CateGoryCallback<List<CateGoryDemo>>() {
            @Override
            public void onSuccess(List<CateGoryDemo> strings) {
                baseModelCallBack.onSuccess(strings);
            }

            @Override
            public List<CateGoryDemo> convert(JsonElement result) {
                Log.e("444", "convert: "+result.toString() );
                List<CateGoryDemo> cateGoryDemos = JsonUtils.jsonToClassList(result, CateGoryDemo.class);
                return cateGoryDemos;
            }

            @Override
            public void onError(String message, int code) {
                baseModelCallBack.onFail(message,code );
            }

            @Override
            public void cancle() {

            }
        });
    }


    @Override
    public void showCategoryList(CateGoryIdBean cateGoryIdBean, BaseModelCallBack<List<CateGoryDemo>, List<CateGoryListBean>,List<ShopBean>,ShopHomeList,LoginBean> baseModelCallBack, LifecycleProvider lifecycleProvider) {
        new HttpClient.Builder()
                .setLifecycleProvider(lifecycleProvider)
                .setApiUrl("category/getCategory")
                .setJsonBody(JsonUtils.classToJson(cateGoryIdBean), true)
                .post()
                .build().request(new CateGoryCallback<List<CateGoryListBean>>() {
            @Override
            public void onSuccess(List<CateGoryListBean> strings) {
                baseModelCallBack.onSuccessList(strings);
            }

            @Override
            public List<CateGoryListBean> convert(JsonElement result) {
                List<CateGoryListBean> cateGoryDemos = JsonUtils.jsonToClassList(result, CateGoryListBean.class);
                Log.e("44444444", "convert: "+cateGoryDemos.toString() );
                return cateGoryDemos;
            }

            @Override
            public void onError(String message, int code) {
                baseModelCallBack.onFail(message,code );
            }

            @Override
            public void cancle() {

            }
        });
    }

    @Override
    public void onError(String msg, int code) {

    }
}
