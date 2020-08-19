package com.example.three.app;

import android.util.Log;

import com.example.httplibary.callback.BaseCallBack;
import com.example.httplibary.callback.BaseCallBacktwo;
import com.example.three.bean.LoginBean;
import com.example.three.bean.Responses;
import com.google.gson.Gson;
import com.google.gson.JsonElement;


/**
 * @date：2020/8/4
 * @describe：
 * @author：FanYaJun
 */
public abstract class HttpCallBacktwo<T> extends BaseCallBacktwo<T> {
    LoginBean responseFanyj;
    @Override
    protected T onConvent(String result) {
        T t = null;
        responseFanyj = new Gson().fromJson(result, LoginBean.class);
        int status = responseFanyj.getStatus();
        switch (status) {
            case -1:
                onError("status", status);
                break;
            default:
                if (isCodeSuccess()) {
                    t = convert(status);
                }
                break;
        }
        Log.i("TAG", "onConvent: " + t.toString());
        return t;
    }

    @Override
    protected boolean isCodeSuccess() {
        if (responseFanyj != null) {
            return responseFanyj.getStatus() == 0;
        }
        return false;
    }
}
