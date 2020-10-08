package com.example.httplibrary.utils;

import android.os.Looper;

/**
 * 项目名：Shopping
 * 包名：  com.example.httplibrary.utils
 * 文件名：ThreadUtils
 * 创建者：liangxq
 * 创建时间：2020/8/3  13:54
 * 描述：TODO
 */
public class ThreadUtils {

    //是否是主线程
    public static boolean isMainThread(){
        return Looper.getMainLooper().getThread().getId()==Thread.currentThread().getId();
    }
}
