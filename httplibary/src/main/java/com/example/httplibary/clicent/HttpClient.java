package com.example.httplibary.clicent;

import android.text.TextUtils;
import android.util.Log;

import com.example.httplibary.callback.BaseCallBack;
import com.example.httplibary.http.HttpGlobalConfig;
import com.example.httplibary.http.HttpManager;
import com.example.httplibary.utils.ApiServer;
import com.example.httplibary.utils.HttpConstantUtils;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * @date：2020/8/4
 * @describe：
 * @author：FanYaJun
 */
public class HttpClient {
    Method method;//请求方式
    Map<String,Object> params;//请求参数信息
    Map<String,Object> heades;//请求头信息
    LifecycleProvider lifecycleProvider;//Rxjava绑定生命周期器
    ActivityEvent activityEvent;//activity绑定寿命周期
    FragmentEvent fragmentEvent;//Fragment绑定生命周期
    String baseuri;
    String apiuri;//拼接的uri；
    long timeout;
    TimeUnit timeUnit;
    //是否是json上串
    boolean isJson;
    //json字符串
    String jsonbody;
    //回调的借口
    BaseCallBack baseCallBack;
    //订阅关系的标签
    String tag;

    public HttpClient(Builder builder) {
        this.method=builder.method;//请求数据
        this.activityEvent=builder.activityEvent;//绑定A
        this.fragmentEvent=builder.fragmentEvent;//绑定F
        this.params=builder.paramser;//请求参数
        this.heades=builder.headres;//请求头信息
        this.lifecycleProvider=builder.lifecycleProvider;//Rxjava绑定上下文
        this.baseuri=builder.baseUrl;//uri路径
        this.apiuri=builder.apiUrl;//拼接uri
        this.isJson=builder.isJson;//是否是json串
        this.jsonbody=builder.jsonbody;//传入的是json字符串
        this.timeout=builder.time;//超时时间
        this.timeUnit=builder.timeUnit;//时分秒
        this.tag=builder.tag;//参数标示
    }
    public void requset(BaseCallBack baseCallBack){
        if(baseCallBack==null){
            new RuntimeException("no hava callback,musk do observer");
        }
        this.baseCallBack=baseCallBack;
        doRequset();
    }


    private void doRequset() {
        //组装Obserable,并且根据请求方式返回对应的Obserable，去处理异常结果的回调
        if (TextUtils.isEmpty(tag)) {
            tag = System.currentTimeMillis() + "";
        }
        baseCallBack.setTag(tag);
        //添加参数信息
        addPramaers();
        //添加头信息
        addHeadrs();
        if (HttpGlobalConfig.getInstance().getBaseUri() != null) {
            this.baseuri = HttpGlobalConfig.getInstance().getBaseUri();
        }
        Observable observable = createObservable();
        HttpObserable httpObserable = new HttpObserable.Builder(observable)
                .setActivityEvent(activityEvent)
                .setFragmentEvent(fragmentEvent)
                .setBaseObserver(baseCallBack)
                .build();
        httpObserable.observer().subscribe(baseCallBack);
    }
    private void addHeadrs() {
        if (heades == null) {
            heades = new HashMap<>();
        }
        if (HttpGlobalConfig.getInstance().getHeaderparmeds() != null) {
            heades.putAll(HttpGlobalConfig.getInstance().getHeaderparmeds());
        }
    }

    private void addPramaers() {
        if (params == null) {
            params = new HashMap<>();
        }
        //添加公共的请求参数
        if (HttpGlobalConfig.getInstance().getBaseparmeds() != null) {
            params.putAll(HttpGlobalConfig.getInstance().getBaseparmeds());
        }
    }
    private Observable createObservable() {
        Observable observable = null;
        boolean hasBodyString = !TextUtils.isEmpty(jsonbody);
        RequestBody requestBody = null;
        if (hasBodyString) {
            String mediaType = isJson ? "application/json; charset=utf-8" : "text/plain;charset=utf-8";
            requestBody = RequestBody.create(okhttp3.MediaType.parse(mediaType), jsonbody);
        }
        //默认请求时POST
        if (method == null) {
            method = Method.POST;
        }
        if(HttpGlobalConfig.getInstance().getTimeout()!=0){
            this.timeout=HttpGlobalConfig.getInstance().getTimeout();
        }
        if(this.timeout==0){
            this.timeout= HttpConstantUtils.TIME_OUT;
        }
        if(HttpGlobalConfig.getInstance().getTimeUnit()!=null){
            this.timeUnit=HttpGlobalConfig.getInstance().getTimeUnit();
        }
        if(this.timeUnit==null){
            this.timeUnit=HttpConstantUtils.TIME_UNIT;
        }
        ApiServer apiServer = HttpManager.getInstance().getRetrofit(baseuri, timeout, timeUnit).create(ApiServer.class);
        switch (method) {
            case POST:
                if (isJson) {
                    apiServer.postjson(apiuri, requestBody, heades);
                } else {
                    apiServer.post(apiuri, params, heades);
                }
                break;
            case GET:
                observable = apiServer.get(apiuri, params, heades);
                break;
            case DELETE:
                observable = apiServer.delete(apiuri, params, heades);
                break;
            case PUT:
                observable = apiServer.put(apiuri, params, heades);
                break;
        }
        return observable;
    }


    public static final class Builder{
        //请求方式
        Method method;
        //请求参数
        Map<String, Object> paramser;
        //请求头信息
        Map<String, Object> headres;
        //Rxjava绑定生命周期
        LifecycleProvider lifecycleProvider;
        //绑定Activity具体的生命周的
        ActivityEvent activityEvent;
        //绑定Fragment的具体的生命周期的
        FragmentEvent fragmentEvent;
        String baseUrl;
        //拼接的url
        String apiUrl;
        //是否是json上传表示
        boolean isJson;
        //json字符串
        String jsonbody;
        //超时时间
        long time;
        //时间单位
        TimeUnit timeUnit;
        //订阅的标签
        String tag;
        //get builder.get
        public Builder get(){
            this.method=Method.GET;
            return this;
        }
        //上传方式使用构建者模式
        //builder.post
        public Builder post(){
            this.method=Method.POST;
            return this;
        }
        //builder.delete
        public Builder delete(){
            this.method=Method.DELETE;
            return this;
        }
        //builder.put
        public Builder put(){
            this.method=Method.PUT;
            return this;
        }
        public Builder() {
        }


        public Builder setParamser(Map<String, Object> paramser) {
            this.paramser = paramser;
            return this;
        }


        public Builder setHeadres(Map<String, Object> headres) {
            this.headres = headres;
            return this;
        }


        public Builder setLifecycleProvider(LifecycleProvider lifecycleProvider) {
            this.lifecycleProvider = lifecycleProvider;
            return this;
        }


        public Builder setActivityEvent(ActivityEvent activityEvent) {
            this.activityEvent = activityEvent;
            return this;
        }


        public Builder setFragmentEvent(FragmentEvent fragmentEvent) {
            this.fragmentEvent = fragmentEvent;
            return this;
        }


        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }


        public Builder setApiUrl(String apiUrl) {

            this.apiUrl = apiUrl;
            return this;
        }


        public Builder setJsonbody(String jsonbody,boolean isJson) {
            this.jsonbody = jsonbody;
            this.isJson=isJson;
            return this;
        }

        public long getTime() {
            return time;
        }

        public Builder setTime(long time) {
            this.time = time;
            return this;
        }


        public Builder setTimeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }


        public Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }
        //创建返回确认数据；
        public HttpClient build(){
            return new HttpClient(this);
        }
    }

}
