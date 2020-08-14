package com.example.mymvplibrary.presenter;


import com.example.mymvplibrary.view.BaseView;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;
import java.util.logging.Handler;

/**
 * 项目名：Shopping
 * 包名：  com.example.mvplibrary.presenter
 * 文件名：BasePresenter
 * 创建者：liangxq
 * 创建时间：2020/8/5  14:38
 * 描述：TODO
 */
public class BasePresenter<V extends BaseView> {
    public V mView;
    private WeakReference<V>weakReference;
    //绑定
    public void attacthView(V v){
        weakReference =new WeakReference<V>(v);
        mView=weakReference.get();
    }
    //返回视图层对象
    public LifecycleProvider getLifeCycle(){
        return (LifecycleProvider) mView;
    }
    //解绑
    public void destoryView(){
        if(weakReference!=null){
            weakReference.clear();
        }
    }
}
