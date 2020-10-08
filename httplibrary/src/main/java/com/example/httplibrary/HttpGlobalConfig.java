package com.example.httplibrary;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;

/**
 * 项目名：Shopping
 * 包名：  com.example.httplibrary
 * 文件名：HttpGlobalConfig
 * 创建者：liangxq
 * 创建时间：2020/8/c  11:05
 * 描述：TODO
 */
public class HttpGlobalConfig {
    //baseUrl
    private String baseUrl;
    private long timeout;
    private TimeUnit timeUnit;
    //公共请求参数
    private Map<String, Object> baseparams;
    //公共的请求头信息
    private Map<String, Object> baseHeades;
    //公共的拦截器
    private List<Interceptor> interceptors;
    //日志开关
    private boolean isShowLog;

    private Context context;

    private Handler handler;

    //存储各种token的map集合
    private Map<String,String>appkeys;

    private HttpGlobalConfig() {
    }

    //静态内部类
    private static final class HttpGlobalConfigHodler {
        private static HttpGlobalConfig INSTANCE = new HttpGlobalConfig();
    }

    public static HttpGlobalConfig getInsance() {
        return HttpGlobalConfigHodler.INSTANCE;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public HttpGlobalConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return HttpGlobalConfig.getInsance();
    }

    public long getTimeout() {
        return timeout;
    }

    public HttpGlobalConfig setTimeout(long timeout) {
        this.timeout = timeout;
        return HttpGlobalConfig.getInsance();
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public HttpGlobalConfig setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return HttpGlobalConfig.getInsance();
    }

    public Map<String, Object> getBaseparams() {
        return baseparams;
    }

    public HttpGlobalConfig setBaseparams(Map<String, Object> baseparams) {
        this.baseparams = baseparams;
        return HttpGlobalConfig.getInsance();
    }

    public Map<String, Object> getBaseHeades() {
        return baseHeades;
    }

    public HttpGlobalConfig setBaseHeades(Map<String, Object> baseHeades) {
        this.baseHeades = baseHeades;
        return HttpGlobalConfig.getInsance();
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public HttpGlobalConfig setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
        return HttpGlobalConfig.getInsance();
    }

    public boolean isShowLog() {
        return isShowLog;
    }

    public HttpGlobalConfig setShowLog(boolean showLog) {
        isShowLog = showLog;
        return HttpGlobalConfig.getInsance();
    }

    public HttpGlobalConfig initReady(Context context){
        this.context=context.getApplicationContext();
        handler=new Handler(Looper.getMainLooper());
        return HttpGlobalConfig.getInsance();
    }

    public Context getContext() {
        return context;
    }

    public Handler getHandler() {
        return handler;
    }
    //配置各种appk
    public HttpGlobalConfig setAppKey(String key,String value){
        if(appkeys==null){
            appkeys=new HashMap<>();
        }
        appkeys.put(key,value);
        return HttpGlobalConfig.getInsance();
    }


    //获取对应的appKey
    public Object getAppkey(String key){
        if(appkeys!=null&&key!=null){
            return appkeys.get(key);
        }
        return null;
    }
}
