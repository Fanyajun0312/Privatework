package com.example.three.model;

import android.util.Log;
import com.example.httplibary.clicent.HttpClient;
import com.example.httplibary.utils.JsonUtils;
import com.example.mymvplibrary.modle.BaseModel;
import com.example.three.Dian;
import com.example.three.app.HttpCallBack;
import com.example.three.callBack.BackCall;
import com.example.three.postBean.oneBean;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date：2020/8/5
 * @describe：
 * @author：FanYaJun
 */
public class MainModel implements BaseModel {
    public void getdata(BackCall backCall) {

        new HttpClient.Builder()
                .setApiUrl("v1/album_categories?channel=new&offset=0&limit=100&addition_album_count=20")
                .get()
                .build()
                .requset(new HttpCallBack<List<Dian>>() {

                    @Override
                    public void onError(String err, int onError) {
                        Log.i("TAG", "onError: "+err);
                    }

                    @Override
                    public void cancle() {

                    }

                    @Override
                    protected void onSuccess(List<Dian> pare) {
                        backCall.okbena(pare);
                        Log.i("TAG", "onSuccess: "+pare.toString());
                    }

                    @Override
                    public List<Dian> convert(JsonElement result) {
                        return JsonUtils.jsonToClassList(result,Dian.class);
                    }
                });
    }
}
