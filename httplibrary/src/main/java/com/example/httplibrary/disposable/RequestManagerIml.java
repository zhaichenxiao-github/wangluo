package com.example.httplibrary.disposable;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.disposables.Disposable;

/**
 * 项目名：Shopping
 * 包名：  com.example.httplibrary.disposable
 * 文件名：RequestManagerIml
 * 创建者：liangxq
 * 创建时间：2020/8/3  9:54
 * 描述：TODO
 */
public class RequestManagerIml implements RequestManager{

    private static volatile RequestManagerIml instance;
    //管理所有的订阅关系的的Map
    private static Map<String,Disposable>mMap=new HashMap<>();
    private RequestManagerIml(){

    }
    public static RequestManagerIml getInstance(){
        if (instance == null) {
            synchronized (RequestManagerIml.class){
                if (instance == null) {
                    instance=new RequestManagerIml();
                }
            }
        }
        return instance;
    }
    @Override
    public void add(String tag, Disposable disposable) {
        if(!TextUtils.isEmpty(tag)){
            mMap.put(tag,disposable);
        }
    }

    @Override
    public void remove(String tag) {
        if(!TextUtils.isEmpty(tag)){
            if(mMap.isEmpty()){
                return;
            }
            mMap.remove(tag);
        }
    }

    @Override
    public void cancle(String tag) {
        if(!TextUtils.isEmpty(tag)){
            if (!mMap.isEmpty()){
                if(mMap.get(tag)!=null){
                    Disposable disposable = mMap.get(tag);
                    if(!disposable.isDisposed()){
                        disposable.dispose();
                    }
                    mMap.remove(tag);
                }
            }
        }
    }

    @Override
    public void cancleAll() {
        Disposable disposable=null;
        if(!mMap.isEmpty()){
            Set<String> keys = mMap.keySet();
            for (String key : keys) {
                if(mMap.get(key)!=null){
                    disposable= mMap.get(key);
                }
                if(disposable!=null&&!disposable.isDisposed()){
                    disposable.dispose();
                }
            }
        }
        mMap.clear();
    }

    public boolean isDispose(String tag){
        if(!mMap.isEmpty()&&mMap.get(tag)!=null){
            return mMap.get(tag).isDisposed();
        }
        return false;
    }
}
