package com.example.wangluo.httpdemo.wanandroid;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.app
 * 文件名：Response
 * 创建者：liangxq
 * 创建时间：2020/8/c  14:58
 * 描述：TODO
 */
public class Response {
    /**
     * 描述信息
     */
    @SerializedName("message")
    private String msg;

    /**
     * 状态码
     */
    @SerializedName("status")
    private int code;

    /**
     * 数据对象/成功返回对象
     */
    @SerializedName("data")
    private JsonElement result;

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return code == 0;
    }

//    public String toString() {
//        String response = "[http response]" + "{\"code\": " + code + ",\"msg\":" + msg + ",\"result\":" + new Gson().toJson(result) + "}";
//        return response;
//    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JsonElement getResult() {
        return result;
    }

    public void setResult(JsonElement result) {
        this.result = result;
    }
}
