package com.example.httplibrary.demo;

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
    private JsonElement data;
    private int errorCode;
    private String errorMsg;

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
