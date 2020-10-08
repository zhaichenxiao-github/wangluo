package com.example.wangluo.app;

import android.app.Application;

import com.example.httplibrary.HttpConstant;
import com.example.httplibrary.HttpGlobalConfig;
import com.example.wangluo.utils.Concasts;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import cn.jpush.android.api.JPushInterface;


/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.app
 * 文件名：ShopApplication
 * 创建者：liangxq
 * 创建时间：2020/8/c  14:12
 * 描述：TODO
 */
public class ShopApplication extends Application {

    private static ShopApplication app;
    private static RefWatcher watcher;


    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        HttpGlobalConfig.getInsance()
                .setBaseUrl("http://169.254.189.205:8080/kotlin/")
//                .setBaseUrl("https://www.wanandroid.com/")
                .setTimeout(HttpConstant.TIME_OUT)
                .setShowLog(true)
                .setAppKey(Concasts.JPUSHREGISTID, JPushInterface.getRegistrationID(this))
                .setTimeUnit(HttpConstant.TIME_UNIT)
                .initReady(this);
        CrashReport.initCrashReport(getApplicationContext(), "14fa4afd7c", false);
//        CrashReport.testJavaCrash();

        /**
         * 注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调
         * 用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
         * UMConfigure.init调用中appkey和channel参数请置为null）。
         */
        UMConfigure.init(getApplicationContext(), "5f50e8c37823567fd8636ece", "Umeng",0 , null);

        //选择AUTO页面采集模式，统计SDK基础指标无需手动埋点可自动采集。
//建议在宿主App的Application.onCreate函数中调用此函数。
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);

        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        watcher = LeakCanary.install(this);
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

    }
    public static ShopApplication getApp(){
        return app;
    }

    public static RefWatcher getRefWatcher() {
        return watcher;
    }
}