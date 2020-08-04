package com.example.httplibary.clicent;

import android.util.Log;

import com.example.httplibary.callback.BaseObserver;
import com.example.httplibary.exception.ExceptionEngine;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @date：2020/8/4
 * @describe：
 * @author：FanYaJun
 */
public class HttpObserable {

    LifecycleProvider lifecycleProvider;
    //绑定Activity具体的生命周的
    ActivityEvent activityEvent;
    //绑定Fragment的具体的生命周期的
    FragmentEvent fragmentEvent;
    Observable observable;
    BaseObserver baseObserver;
    /**
     * 对初始化数据的返回值JsonElement 进行转换操作 类似于map
     *
     */
    public Observable map(){
        return observable.map(new Function<JsonElement,Object>() {
            @Override
            public Object apply(JsonElement o) throws Exception {
                return new Gson().toJson(o);
            }
        });
    }

    /*onErrorResumeNext*/
    //错误信息的分类回调
    /*onErrorResumeNext*/
    //错误信息的分类回调
    public Observable onErrorResumeNext() {
        return bindlifecycle().onErrorResumeNext(new Function<Throwable, ObservableSource>() {
            @Override
            public ObservableSource apply(Throwable throwable) throws Exception {
                Log.i("TAG", "apply: "+throwable.getMessage());
                return Observable.error(ExceptionEngine.handleException(throwable));
            }
        });
    }
    public Observable observer(){
        return doOnDispose().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //监听取消订阅的操作
    /*doOnDispose*/
    public Observable doOnDispose() {
        if (baseObserver != null) {
            return onErrorResumeNext().doOnDispose(new Action() {
                @Override
                public void run() throws Exception {
                    baseObserver.canclend();
                }
            });
        }
        return onErrorResumeNext();
    }

    //Rxjava的生命周期的绑定
    private Observable bindlifecycle() {
        Observable observable = map();
        if (lifecycleProvider != null) {
            if (activityEvent != null || fragmentEvent != null) {
                //两个同时存在,以 activity 为准
                if (activityEvent != null && fragmentEvent != null) {
                    return map().compose(lifecycleProvider.bindUntilEvent(activityEvent));
                }
                if (activityEvent != null) {
                    return map().compose(lifecycleProvider.bindUntilEvent(activityEvent));
                }
                if (fragmentEvent != null) {
                    return map().compose(lifecycleProvider.bindUntilEvent(fragmentEvent));
                }
            } else {
                return map().compose(lifecycleProvider.bindToLifecycle());
            }
        }
        return observable;
    }


    public HttpObserable(Builder builder) {
        this.lifecycleProvider=builder.lifecycleProvider;
        this.activityEvent=builder.activityEvent;
        this.fragmentEvent=builder.fragmentEvent;
        this.observable=builder.observable;
        this.baseObserver=builder.baseObserver;
    }


    public static final class Builder{
        LifecycleProvider lifecycleProvider;
        //绑定Activity具体的生命周的
        ActivityEvent activityEvent;
        //绑定Fragment的具体的生命周期的
        FragmentEvent fragmentEvent;
        Observable observable;
        BaseObserver baseObserver;

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

        public Builder setObservable(Observable observable) {
            this.observable = observable;
            return this;
        }

        public Builder setBaseObserver(BaseObserver baseObserver) {
            this.baseObserver = baseObserver;
            return this;
        }

        /**
         * 参数构造obserable
         * @param observable
         */
        public Builder(Observable observable) {
            this.observable = observable;
        }

        public HttpObserable build(){
            return new HttpObserable(this);
        }
    }
}
