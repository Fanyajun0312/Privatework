package com.example.httplibary.callback;

import android.util.Log;

import com.example.httplibary.exception.ExceptionEngine;
import com.google.gson.JsonElement;

/**
 * @date：2020/8/4
 * @describe：处理数据 数据的返回状态等等
 * @author：FanYaJun
 */
public abstract class BaseCallBacktwo<T> extends BaseObserver {
    //解析成功的标志
    boolean ClassSuccess = true;

    @Override
    public void onNext(Object t) {
        super.onNext(t);
        Log.e("TAG", "onNext:111" + t.toString());//数据
        T pare = pare((String) t);
        Log.e("TAG", "onNext: 222" + pare.toString());
        if (ClassSuccess && isCodeSuccess()) {
            onSuccess(pare);
        }
    }


    public T pare(String result) {
        T data = null;
        try {
            data = onConvent(result);
            ClassSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            ClassSuccess = false;
            onError("数据类型解析错误", ExceptionEngine.ANALYTIC_SERVER_DATA_ERROR);
        }
        return data;
    }

    //将JsonElement转换为Response，并且通过子类的实现来获取data数据
    protected abstract T onConvent(String result);

    protected abstract void onSuccess(T pare);

    protected abstract boolean isCodeSuccess();

    public abstract T convert(int result);
}
