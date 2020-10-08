package com.example.wangluo.mvp;

public interface ModelSubCallBack<T> {
    void OnSuccessSub(T t);
    void OnError(String msg,int code);
}
