package com.example.three.app;

import android.app.Application;

import com.example.httplibary.http.HttpGlobalConfig;
import com.example.httplibary.utils.HttpConstantUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @date：2020/8/3
 * @describe：
 * @author：FanYaJun
 */
public class ShopApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpGlobalConfig.getInstance()
                .setBaseUri("https://www.wanandroid.com/")
                .setTimeout(HttpConstantUtils.TIME_OUT)
                .setTimeUnit(HttpConstantUtils.TIME_UNIT)
                .initReady(this)
                .isShowLog();
    }
}
