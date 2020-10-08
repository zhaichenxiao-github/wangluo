package com.example.wangluo.httpdemo.wanandroid;

import com.google.gson.JsonElement;

/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.httpdemo.wanandroid
 * 文件名：WanResponse
 * 创建者：liangxq
 * 创建时间：2020/8/4  15:54
 * 描述：TODO
 */
public class WanResponse {
    JsonElement data;
    int errorCode;
    String errorMsg;

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
