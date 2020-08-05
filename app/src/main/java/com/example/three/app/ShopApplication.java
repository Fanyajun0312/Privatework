package com.example.three.app;

import android.app.Application;

import com.example.httplibary.http.HttpGlobalConfig;
import com.example.httplibary.utils.HttpConstantUtils;
import com.example.three.MainActivity;

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
                .setBaseUri("http://api.t.ergedd.com/api/")
                .setTimeout(HttpConstantUtils.TIME_OUT)
                .setTimeUnit(HttpConstantUtils.TIME_UNIT)
                .initReady(this)
                .isShowLog();
    }
}
