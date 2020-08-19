package com.example.three.app;

import android.app.Application;

import com.example.httplibary.http.HttpGlobalConfig;
import com.example.httplibary.utils.HttpConstantUtils;
import com.example.three.MainActivity;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import jy.com.libumengsharelogin.UMUtils;


/**
 * @date：2020/8/3
 * @describe：
 * @author：FanYaJun
 */
public class ShopApplication extends Application {
    public static ShopApplication app;
    @Override
    public void onCreate() {
        super.onCreate();
//        youMen();//友盟
        tengXunBug();//腾讯异常上报
        httpNetWork();//网络请求
        LeakCanary();//防止侧漏
        UMUtils.initUmeng(this);
        UMUtils.isWeixinAvilible(this);
        UMUtils.isAliPayInstalled(this);
        app=this;
    }

    public static ShopApplication getApp() {
        return app;
    }

    private void LeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for
            // heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);

    }

    private void httpNetWork() {
        HttpGlobalConfig.getInstance()
                .setBaseUri("http://169.254.189.205:8080/")
                .setTimeout(HttpConstantUtils.TIME_OUT)
                .setTimeUnit(HttpConstantUtils.TIME_UNIT)
                .initReady(this)
                .isShowLog();
    }

    private void tengXunBug() {
        CrashReport.initCrashReport(getApplicationContext(), "495b0201e4", false);
//        CrashReport.testJavaCrash();

    }

    private void youMen() {
//        UMConfigure.init(this, "5f2b76f7d3093221547568c4", "UmengFan",UMConfigure.DEVICE_TYPE_PHONE,"");
//        UMConfigure.setLogEnabled(true);
    }
}
