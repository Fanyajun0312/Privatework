package com.example.httplibary.callback;

import android.text.TextUtils;

import com.example.httplibary.http.HttpGlobalConfig;
import com.example.httplibary.disposable.RequestManagerCig;
import com.example.httplibary.exception.ApiException;
import com.example.httplibary.exception.ExceptionEngine;
import com.example.httplibary.utils.ThreadUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @date：2020/8/3
 * @describe：
 * @author：FanYaJun
 */
public abstract class BaseObserver implements Observer {//基类observer实现observer的方法

    String tag;
    @Override
    public void onSubscribe(Disposable d) {
        if (!TextUtils.isEmpty(tag)) {
            RequestManagerCig.getInstance().addDispouable(tag, d);
        }
    }
    @Override
    public void onNext(Object t) {
        if (TextUtils.isEmpty(tag)) {
            RequestManagerCig.getInstance().remove(tag);

        }
    }
    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException){
            RequestManagerCig.getInstance().remove(e.getMessage());
        }else {
            onError("未知异常",ExceptionEngine.UN_KNOWN_ERROR);
        }
        if(!TextUtils.isEmpty(tag)){
            RequestManagerCig.getInstance().remove(tag);
        }
    }

    @Override
    public void onComplete() {
        if(RequestManagerCig.getInstance().isDispoable(tag)){
            RequestManagerCig.getInstance().concle(tag);
        }
    }
    //回调错错误信息
    public abstract void onError(String err, int onError);
    public abstract void cancle();

    //网络请求取消
    public void canclend(){
        if(!ThreadUtils.isMainThreand()){
            HttpGlobalConfig.getInstance().getHandler().post(new Runnable() {
                @Override
                public void run() {
                    cancle();
                }
            });
        }
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
}
