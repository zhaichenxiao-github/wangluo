package com.example.httplibrary;

import com.example.httplibrary.utils.LogUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 项目名：Shopping
 * 包名：  com.example.httplibrary
 * 文件名：HttpManager
 * 创建者：liangxq
 * 创建时间：2020/8/c  10:33
 * 描述：TODO
 */
public class HttpManager {
    private static volatile HttpManager instance;
    private HttpManager() {
    }
    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit(String baseUrl, long timeout, TimeUnit timeUnit) {
        return new Retrofit.Builder()
                .client(getBaseOkhttpClient(timeout, timeUnit))
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getBaseOkhttpClient(long timeout, TimeUnit timeUnit) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.e("okhttp===" + message);
            }
        });

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response proceed = chain.proceed(request);
                return proceed;
            }
        };
        Interceptor[] interceptors = {httpLoggingInterceptor, interceptor,new CookieIncepter()};
        return getCliect(timeout, timeUnit, interceptors);
    }

    public OkHttpClient getCliect(long timeout, TimeUnit timeUnit, Interceptor... interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(timeout, timeUnit);
        builder.writeTimeout(timeout, timeUnit);
        builder.readTimeout(timeout, timeUnit);
        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }
        List<Interceptor> baseIntercepts = HttpGlobalConfig.getInsance().getInterceptors();
        //公共请求头或者参数的添加
        if (baseIntercepts != null) {
            for (Interceptor baseIntercept : baseIntercepts) {
                builder.addInterceptor(baseIntercept);
            }
        }
        return builder
                .build();
    }
}