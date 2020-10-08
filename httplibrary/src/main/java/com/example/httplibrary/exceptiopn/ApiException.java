package com.example.httplibrary.exceptiopn;

/**
 * 项目名：Shopping
 * 包名：  com.example.httplibrary.exceptiopn
 * 文件名：ApiException
 * 创建者：liangxq
 * 创建时间：2020/8/3  10:23
 * 描述：TODO
 */
public class ApiException extends Throwable{

    String msg;
    int code;
    public ApiException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

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
}
