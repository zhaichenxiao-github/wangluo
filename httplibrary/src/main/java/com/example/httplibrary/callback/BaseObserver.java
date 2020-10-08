package com.example.httplibrary.callback;

import android.text.TextUtils;

import com.example.httplibrary.HttpGlobalConfig;
import com.example.httplibrary.disposable.RequestManagerIml;
import com.example.httplibrary.exceptiopn.ApiException;
import com.example.httplibrary.exceptiopn.ExceptionEngine;
import com.example.httplibrary.utils.ThreadUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 项目名：Shopping
 * 包名：  com.example.httplibrary.callback
 * 文件名：BaseObserver
 * 创建者：liangxq
 * 创建时间：2020/8/c  14:15
 * 描述：TODO
 */
public abstract class BaseObserver implements Observer{
    String tag;
    @Override
    public void onSubscribe(Disposable d) {
        if(!TextUtils.isEmpty(tag)){
            RequestManagerIml.getInstance().add(tag,d);
        }
    }

    @Override
    public void onNext(Object t) {
        if(!TextUtils.isEmpty(tag)){
            RequestManagerIml.getInstance().remove(tag);
        }
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException){
            ApiException apiException= (ApiException) e;
            onError(apiException.getMsg(),apiException.getCode());
        }else{
            onError("未知异常", ExceptionEngine.UN_KNOWN_ERROR);
        }
        if(!TextUtils.isEmpty(tag)){
            RequestManagerIml.getInstance().remove(tag);
        }
    }

    @Override
    public void onComplete() {
        if(!RequestManagerIml.getInstance().isDispose(tag)){
            RequestManagerIml.getInstance().cancle(tag);
        }
    }

    //回调错错误信息
     public abstract void onError(String message, int code);


     public abstract void cancle();


    //网络请求取消
    public void canclend(){
        if(!ThreadUtils.isMainThread()){
            HttpGlobalConfig.getInsance().getHandler().post(new Runnable() {
                @Override
                public void run() {
                    cancle();
                }
            });
        }
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
}
