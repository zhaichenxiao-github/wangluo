package com.example.httplibrary.disposable;

import io.reactivex.disposables.Disposable;

/**
 * 项目名：Shopping
 * 包名：  com.example.httplibrary.disposable
 * 文件名：RequestManager
 * 创建者：liangxq
 * 创建时间：2020/8/3  9:49
 * 描述：TODO
 * <p>
 * 订阅关系关联的类
 */
public interface RequestManager {
    /**
     * @param tag        订阅关系的标识
     * @param disposable 订阅关系
     */
    void add(String tag, Disposable disposable);

    //移除订阅关系
    void remove(String tag);

    //取消订阅
    void cancle(String tag);

    //取消所有的订阅
    void cancleAll();
}
