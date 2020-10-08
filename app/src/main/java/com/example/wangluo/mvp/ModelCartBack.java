package com.example.wangluo.mvp;

public interface ModelCartBack<T> {
    void OnSuccessCart(T t);
    void OnError();
}
