package com.example.mymvplibrary.presenter;

import com.example.mymvplibrary.modle.ModleFractory;
import com.example.mymvplibrary.view.BaseView;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;

/**
 * @date：2020/8/5
 * @describe：
 * @author：FanYaJun
 */
public class BasePresenter<V extends BaseView>  {
    public V mview;
    private WeakReference<V> weakReference;//弱引用变量 当走到这里的时候就会别GC
    private WeakReference<V> reference;

    public void attacthView1(V v){
        reference = new WeakReference<>(v);
        mview=weakReference.get();
    }
    //布局绑定
    public void  attacthView(V v){

        weakReference=new WeakReference<V>(v);
        mview=weakReference.get();
    }
    public LifecycleProvider getlifecycle(){
        return (LifecycleProvider) mview;
    }

    public void destroyView(){
        if(weakReference!=null){
            weakReference.clear();
        }
    }
}
