package com.example.three;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.httplibary.clicent.HttpClient;
import com.example.httplibary.http.HttpGlobalConfig;
import com.example.httplibary.http.HttpManager;
import com.example.httplibary.utils.HttpConstantUtils;
import com.example.httplibary.utils.JsonUtils;
import com.example.three.app.HttpCallBack;
import com.example.three.bean.Demo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                new HttpClient.Builder()
                        .setApiUrl("article/list/0/json")
                        .get()
                        .build()
                        .requset(new HttpCallBack<Demo>() {

                            @Override
                            public void onError(String err, int onError) {
                                Log.i("TAG", "onError: "+err+onError);
                            }

                            @Override
                            public void cancle() {

                            }

                            @Override
                            protected void onSuccess(Demo pare) {
                                Log.i("TAG", "onSuccess: "+pare.toString());
                            }

                            @Override
                            public Demo convert(JsonElement result) {
                                return new Gson().fromJson(result,Demo.class);
                            }
                        });
       }
     
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
