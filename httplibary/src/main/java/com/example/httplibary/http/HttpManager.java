package com.example.httplibary.http;

import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @date：2020/8/1
 * @describe：
 * @author：FanYaJun
 */
public class HttpManager {
    private static volatile HttpManager instance;

    /**
     * 双检索单例模式
     * @return
     */
    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance==null) {
                    instance=new HttpManager();
                }
            }
        }
        return instance;
    }
    /**
     * 定义返回值是retrofit的方法 传参数
     * @param baseUri 网络路径
     * @param timeout 请求时间
     * @param timeUnit 时分秒
     * @return
     */
    public Retrofit getRetrofit(String baseUri, long timeout, TimeUnit timeUnit){
        return new Retrofit.Builder()
                .client(getHttpCilck(timeout,timeUnit))
                .baseUrl(baseUri)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 定义okhttp为返回值的拦截器httplogInterceptor
     * @HttpLoggingInterceptor logger
     * @param timeout
     * @param timeUnit
     * @return
     */
    private OkHttpClient getHttpCilck(long timeout, TimeUnit timeUnit) {
        HttpLoggingInterceptor httplogInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("TAG", "log: " + message);
            }
        });
        Interceptor interceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                return response;
            }
        };
        Interceptor[] interceptors={httplogInterceptor,interceptor};
        return getClick(timeout,timeUnit,interceptors);
    }

    private OkHttpClient getClick(long timeout, TimeUnit timeUnit, Interceptor... interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(timeout,timeUnit);
        builder.writeTimeout(timeout,timeUnit);
        builder.readTimeout(timeout,timeUnit);
        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }
        List<Interceptor> baseIntercepts = HttpGlobalConfig.getInstance().getInterceptors();

        if (baseIntercepts != null) {
            for (Interceptor baseIntercept : baseIntercepts) {
                builder.addInterceptor(baseIntercept);
            }
        }
        return builder
                .build();

    }
}
