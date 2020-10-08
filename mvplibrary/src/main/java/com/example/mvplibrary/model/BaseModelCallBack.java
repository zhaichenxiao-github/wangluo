package com.example.mvplibrary.model;

import android.icu.lang.UCharacter;

public interface BaseModelCallBack<T,V,M,K,H> {
    void onSuccess(T t);
    void onSuccessList(V v);
    void onSuccessShopList(M m);
    void onSuccessShopHomeList(K k);
    void onSuccessLogin(H h);
    void onFail(String msg,int code);
    void cancle();
}
