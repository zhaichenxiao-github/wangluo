package com.example.wangluo.httpdemo.wanandroid;

import android.util.Log;

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
public abstract class HttpCallBack<T> extends BaseCallBack<T> {
    WanResponse wanResponse;
    @Override
    protected T onConvert(String result) {
        T t=null;
        wanResponse = new Gson().fromJson(result, WanResponse.class);
        JsonElement data = wanResponse.getData();
        int errorCode = wanResponse.getErrorCode();
        String errorMsg = wanResponse.getErrorMsg();
        switch (errorCode) {
            case -1001:
                onError("登录失效",errorCode);
                break;
            default:
                if (isCodeSuccess()) {
                    t=convert(data);
                }
                break;
        }
        Log.e("liangxq", "onConvert: "+t.toString() );
        return t;
    }


    @Override
    public boolean isCodeSuccess() {
        if (wanResponse != null) {
            return wanResponse.getErrorCode() == 0;
        }
        return false;
    }

}
