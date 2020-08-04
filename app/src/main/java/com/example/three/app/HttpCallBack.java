package com.example.three.app;

import android.util.Log;

import com.example.httplibary.callback.BaseCallBack;
import com.example.three.bean.Response;
import com.google.gson.Gson;
import com.google.gson.JsonElement;


/**
 * @date：2020/8/4
 * @describe：
 * @author：FanYaJun
 */
public abstract class HttpCallBack<T> extends BaseCallBack<T> {
    Response responseFanyj;
    @Override
    protected T onConvent(String result) {
        T t=null;
        responseFanyj = new Gson().fromJson(result, Response.class);
        JsonElement data = responseFanyj.getData();
        int errorCode = responseFanyj.getErrorCode();
        String errorMsg = responseFanyj.getErrorMsg();
            switch(errorCode) {
                    case -1:
                        onError(errorMsg,errorCode);
                        break;
                default:
                    if(isCodeSuccess()){
                     t=convert(data);
                    }
                    break;
                }
        Log.i("TAG", "onConvent: "+t.toString());
        return t;
    }

    @Override
    protected boolean isCodeSuccess() {
        if(responseFanyj!=null){
            return responseFanyj.getErrorCode()== 0;
        }
        return false;
    }
}
