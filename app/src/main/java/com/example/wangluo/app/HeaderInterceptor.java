package com.example.wangluo.app;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.app
 * 文件名：HeaderInterceptor
 * 创建者：liangxq
 * 创建时间：2020/8/3  9:37
 * 描述：TODO
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
//        builder.addHeader("Time-Stamp", time_stamp);
//        builder.addHeader("Device-Key", device_key);
//        builder.addHeader("Version", getVersion());
//        builder.addHeader("Authorization", ChannelUtil.getAPIKEY() + ":" + "Android");
//        builder.addHeader("channel", ChannelUtil.getChannel());
        builder.removeHeader("User-Agent");
        builder.addHeader("User-Agent", "apps/api");
        Request build = builder.build();
        return chain.proceed(build);
    }
}
