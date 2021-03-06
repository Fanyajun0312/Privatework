package com.example.mymvplibrary.base;

import android.os.Bundle;


import androidx.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名：Shopping
 * 包名：  com.example.mvplibrary.base
 * 文件名：BaseActivity
 * 创建者：liangxq
 * 创建时间：2020/8/5  15:20
 * 描述：TODO
 */
public abstract class BaseActivity extends RxAppCompatActivity {
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        unbinder = ButterKnife.bind(this);
        initView();
        initEvent();
        initData();
    }

    protected abstract void initView();

    protected abstract void initEvent();

    protected abstract void initData();

    protected abstract int initLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder!=null){
            unbinder.unbind();
        }
    }
}
