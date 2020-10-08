package com.example.wangluo.httpdemo.shopping;

import com.google.gson.JsonElement;

/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.httpdemo.shopping
 * 文件名：ShopResponse
 * 创建者：liangxq
 * 创建时间：2020/8/4  16:02
 * 描述：TODO
 */
public class ShopResponse {
    int status;
    String message;
    JsonElement data;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }
}
