package com.example.wangluo;

import android.content.Context;

public class SingLeton {
    private static SingLeton singleton;
    private Context context;
    private SingLeton(Context context) {
        this.context = context;
    }
    public static SingLeton newInstance(Context context) {
        if (singleton == null) {
            synchronized (SingLeton.class) {
                if (singleton == null){//双重检查锁定
                    singleton = new SingLeton(context);
                }
            }
        }
        return singleton;
    }
}