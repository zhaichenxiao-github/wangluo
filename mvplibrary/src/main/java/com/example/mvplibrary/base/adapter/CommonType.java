package com.example.mvplibrary.base.adapter;

public interface CommonType<DATA> {
    //根据不同类型返回对应的布局文件
    int getTypeLayoutId(int viewType);
    //返回类型
    int getType(int position,DATA data);
}
