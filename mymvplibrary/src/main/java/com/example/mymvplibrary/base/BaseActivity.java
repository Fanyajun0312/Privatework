package com.example.mymvplibrary.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @date：2020/8/5
 * @describe：
 * @author：FanYaJun
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());//布局
        bind = ButterKnife.bind(this);//控件
        inirEvent();//处理事件
        initData();//处理数据
        initListener();
    }


    protected abstract int getLayoutView();
    protected abstract void inirEvent();
    protected abstract void initData();
    protected abstract void initListener();


    //解除空间绑定
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bind!=null){
            bind.unbind();

        }
    }
}
