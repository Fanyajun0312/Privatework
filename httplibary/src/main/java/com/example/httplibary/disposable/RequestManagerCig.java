package com.example.httplibary.disposable;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.disposables.Disposable;

/**
 * @date：2020/8/3
 * @describe：
 * @author：FanYaJun
 */
public class RequestManagerCig implements RequestManagerDis {

    private static volatile RequestManagerCig instance;
    //静态的map集合管理所有的订阅关系
    private static Map<String, Disposable> maps = new HashMap<>();

    public static RequestManagerCig getInstance() {
        if (instance == null) {
            synchronized (RequestManagerCig.class) {
                if (instance == null) {
                    instance = new RequestManagerCig();
                }
            }
        }
        return instance;
    }

    public RequestManagerCig() {
    }

    @Override
    public void addDispouable(String tag, Disposable disposable) {
        if (!TextUtils.isEmpty(tag)) {
            maps.put(tag, disposable);
        }
    }

    @Override
    public void remove(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            if (maps.isEmpty()) {
                return;
            }
            maps.remove(tag);
        }
    }

    @Override
    public void concle(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            if (!maps.isEmpty()) {
                if (maps.get(tag) != null) {
                    Disposable disposable = maps.get(tag);
                    if (disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    maps.remove(tag);
                }

            }
        }
    }

    @Override
    public void concleAll() {
        Disposable disposable=null;
        if(!maps.isEmpty()){
            Set<String> tags = maps.keySet();
            for (String tag : tags) {
                if(maps.get(tag)!=null){
                    disposable=maps.get(tag);
                }
                if(disposable!=null&&!disposable.isDisposed()){
                    disposable.dispose();
                }
            }
        }
        maps.clear();
    }
    //创建判断他的方法是否解除关联
    public boolean isDispoable(String tag){
        if(!maps.isEmpty()&&maps.get(tag)!=null){
            return maps.get(tag).isDisposed();

        }
        return false;
    }
}
