package com.example.httplibrary;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CookieIncepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        List<String> headers = proceed.headers("Set-Cookie");
        if (headers!=null){
//            SpUtils.setParam(context, "Set-Cookie",headers);
            EventBus.getDefault().postSticky(headers);
        }
        return proceed;
    }
}
