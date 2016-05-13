package com.yuxuan.apistoredemo;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;

public class Mylication extends Application{
    @Override
    public void onCreate() {
        ApiStoreSDK.init(this,"2c238fba1b1153bf8894d13f0858ccc4");
        super.onCreate();
    }
}
