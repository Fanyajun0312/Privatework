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

    public void attacthView1(V v){
        weakReference = new WeakReference<>(v);
        mview=weakReference.get();
    }
    //布局绑定
    public void  attacthView(V v){

        weakReference=new WeakReference<V>(v);
        mview=weakReference.get();
    }
    //rxlifecycle2 自动干里生命周期的作用 ,避免内存溢出
    public LifecycleProvider getlifecycle(){
        return (LifecycleProvider) mview;
    }

    public void destroyView(){
        if(weakReference!=null){
            weakReference.clear();
        }
    }
}
