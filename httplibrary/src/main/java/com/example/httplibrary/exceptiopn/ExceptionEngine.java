package com.example.httplibrary.exceptiopn;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;


/**
 * 错误/异常处理工具
 *
 * @author ZhongDaFeng
 */
public class ExceptionEngine {

    public static final int UN_KNOWN_ERROR = 1000;//未知错误
    public static final int ANALYTIC_SERVER_DATA_ERROR = 1001;//解析(服务器)数据错误
    public static final int ANALYTIC_CLIENT_DATA_ERROR = 1002;//解析(客户端)数据错误
    public static final int CONNECT_ERROR = 1003;//网络连接错误
    public static final int TIME_OUT_ERROR = 1004;//网络连接超时
    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpExc = (HttpException) e;
            ex = new ApiException("网络错误"+e.getMessage(), httpExc.code());
            //均视为网络错误
            return ex;
        } else if (e instanceof ServerException) {    //服务器返回的错误(交由开发者自己处理)
            ServerException serverExc = (ServerException) e;
            ex = new ApiException(serverExc.getMsg(),serverExc.getCode());
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {  //解析数据错误
            ex = new ApiException("网络解析错误"+e.getMessage(), ANALYTIC_SERVER_DATA_ERROR);
            return ex;
        } else if (e instanceof ConnectException || e instanceof SSLHandshakeException || e instanceof UnknownHostException) {//连接网络错误
            ex = new ApiException("连接失败"+e.getMessage(), CONNECT_ERROR);
            return ex;
        } else if (e instanceof SocketTimeoutException) {//网络超时
            ex = new ApiException("网络超时"+e.getMessage(), TIME_OUT_ERROR);
            return ex;
        } else {  //未知错误
            ex = new ApiException("未知错误"+e.getMessage(), UN_KNOWN_ERROR);
            return ex;
        }
    }

}
