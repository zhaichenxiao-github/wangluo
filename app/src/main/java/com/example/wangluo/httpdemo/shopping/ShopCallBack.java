package com.example.wangluo.httpdemo.shopping;

import com.example.httplibrary.callback.BaseCallBack;
import com.google.gson.JsonElement;

/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.httpdemo.shopping
 * 文件名：ShopCallBack
 * 创建者：liangxq
 * 创建时间：2020/8/4  16:05
 * 描述：TODO
 */
public abstract class ShopCallBack<T> extends BaseCallBack<T>{
    ShopResponse shopResponse;
    @Override
    protected T onConvert(String result) {
        T t=null;
        JsonElement data = shopResponse.getData();
        String message = shopResponse.getMessage();
        int status = shopResponse.getStatus();
        switch (status){
            case 1:
                break;
            default:
                if(isCodeSuccess()){
                   t= convert(data);
                }
                break;
        }
        return t;
    }

    @Override
    public boolean isCodeSuccess() {
        if(shopResponse!=null){
            return shopResponse.getStatus()==0;
        }
        return false;
    }
}
