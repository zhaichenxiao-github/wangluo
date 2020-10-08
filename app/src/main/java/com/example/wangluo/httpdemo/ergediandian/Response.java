package com.example.wangluo.httpdemo.ergediandian;

import com.google.gson.JsonElement;

/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.app
 * 文件名：Response
 * 创建者：liangxq
 * 创建时间：2020/8/c  14:58
 * 描述：TODO
 */
public class Response {
    boolean success;
    JsonElement data;
    String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
