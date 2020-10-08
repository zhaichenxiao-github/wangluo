package com.example.wangluo.httpdemo.ergediandian;

import com.example.httplibrary.callback.BaseCallBack;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * 项目名：Shopping
 * 包名：  com.example.httplibrary.callback
 * 文件名：EgddCallBack
 * 创建者：liangxq
 * 创建时间：2020/8/3  14:29
 * 描述：TODO
 * <p>
 * 示例
 */
public abstract class EgddCallBack<T> extends BaseCallBack<T> {
    Response response;
    @Override
    protected T onConvert(String result) {
        T t=null;
        response = new Gson().fromJson(result, Response.class);
        JsonElement data = response.getData();
        String message = response.getMessage();
        if (isCodeSuccess()){
            t=convert(data);
        }else{
            onError(message,-1);
        }
        return t;
    }


    @Override
    public boolean isCodeSuccess() {
        if (response != null) {
            return response.isSuccess();
        }
        return false;
    }

}
