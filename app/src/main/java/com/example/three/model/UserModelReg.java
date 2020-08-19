package com.example.three.model;

import com.example.three.app.ApiServer;
import com.example.mymvplibrary.modle.BaseModel;
import com.example.three.bean.LoginBean;
import com.example.three.callBack.UserCallback;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @date：2020/8/19
 * @describe：
 * @author：FanYaJun
 */
public class UserModelReg implements BaseModel {

    public void getData(LifecycleProvider lifeCycle, UserCallback callback, String phone, String pwd, String code) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServer.uri)

                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiServer apiServer = retrofit.create(ApiServer.class);
        HashMap<String, Object> pos = new HashMap<>();
        pos.put("mobile", phone);
        pos.put("pwd", pwd);
        pos.put("verifyCode", code);
        HashMap<String, Object> pos1 = new HashMap<>();
        pos1.put("Content-Type","application/json");
        apiServer.getpost(pos,pos1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        callback.onchenggong(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getMessage(), -1);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
