package com.example.wangluo.bean;

import android.util.Log;

import com.example.httplibrary.callback.BaseCallBack;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public abstract class CateGoryCallback<T> extends BaseCallBack<T> {
    CateGoryResponse cateGoryResponse;

    @Override
    protected T onConvert(String result) {
        T t=null;
        cateGoryResponse = new Gson().fromJson(result, CateGoryResponse.class);
        String message = cateGoryResponse.getMessage();
        JsonElement data = cateGoryResponse.getData();
        int status = cateGoryResponse.getStatus();
        switch (status){
            case -1:
                onError(message, status);
                break;
            case 0:
                if (isCodeSuccess()){
                    t=convert(data);
                }
                break;
        }
        Log.e("555", "onConvert: "+t.toString() );
        return t;
    }

    @Override
    public boolean isCodeSuccess() {
        if (cateGoryResponse!=null){
            return cateGoryResponse.getStatus()==0;
        }
        return false;
    }
}
