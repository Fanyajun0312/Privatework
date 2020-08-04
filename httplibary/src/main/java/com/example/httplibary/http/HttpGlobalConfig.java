package com.example.httplibary.http;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;

/**
 * @date：2020/8/1
 * @describe：
 * @author：FanYaJun
 */
public class HttpGlobalConfig {
    private Context context;
    private String baseUri;
    private long timeout;
    private TimeUnit timeUnit;
    private List<Interceptor> interceptors;
    private Map<String,Object> baseparmeds;
    private Map<String,Object> headerparmeds;
    private Handler handler;
    private boolean isShowLog;

    public HttpGlobalConfig() {
    }
    /**
     * 内部类实现链式调用变量
     */
    public HttpGlobalConfig setHeaderparmeds(Map<String, Object> headerparmeds) {
        this.headerparmeds = headerparmeds;
        return HttpGlobalConfighander.INSTARTEN;
    }


    public Map<String, Object> getHeaderparmeds() {
        return headerparmeds;
    }

    public static final class HttpGlobalConfighander{
        public static HttpGlobalConfig INSTARTEN=new HttpGlobalConfig();
    }
    //构造get本类方法公共的注意public
    public static HttpGlobalConfig getInstance(){
        return HttpGlobalConfighander.INSTARTEN;
    }

    public Context getContext() {
        return context;
    }



    public HttpGlobalConfig setContext(Context context) {
        this.context = context;
        return HttpGlobalConfig.getInstance();
    }

    public String getBaseUri() {
        return baseUri;
    }

    public HttpGlobalConfig setBaseUri(String baseUri) {
        this.baseUri = baseUri;
        return HttpGlobalConfig.getInstance();
    }

    public long getTimeout() {
        return timeout;
    }

    public HttpGlobalConfig setTimeout(long timeout) {
        this.timeout = timeout;
        return HttpGlobalConfig.getInstance();

    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public HttpGlobalConfig setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return HttpGlobalConfig.getInstance();
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public HttpGlobalConfig setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
        return HttpGlobalConfig.getInstance();

    }

    public Map<String, Object> getBaseparmeds() {
        return baseparmeds;
    }

    public HttpGlobalConfig setBaseparmeds(Map<String, Object> baseparmeds) {
        this.baseparmeds = baseparmeds;
        return HttpGlobalConfig.getInstance();

    }

    /**
     *
     * @param context
     * @return
     */
    public HttpGlobalConfig initReady(Context context){
        this.context=context.getApplicationContext();
        handler=new Handler(Looper.getMainLooper());
        return HttpGlobalConfig.getInstance();
    }


    public Handler getHandler() {
        return handler;
    }
    public boolean isShowLog() {
        return isShowLog;
    }

    public HttpGlobalConfig setShowLog(boolean showLog) {
        this.isShowLog = showLog;
        return HttpGlobalConfig.getInstance();
    }
    public HttpGlobalConfig setHandler(Handler handler) {
        this.handler = handler;
        return HttpGlobalConfig.getInstance();

    }
}
