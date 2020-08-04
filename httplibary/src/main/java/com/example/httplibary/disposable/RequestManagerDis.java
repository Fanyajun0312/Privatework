package com.example.httplibary.disposable;

import io.reactivex.disposables.Disposable;

/**
 * @date：2020/8/3
 * @describe：
 * @author：FanYaJun
 */
public interface RequestManagerDis {
    /**
     * 添加 移除 取消订阅 取消所有订阅
     */
    void  addDispouable(String tag, Disposable disposable);
    //移除
    void  remove(String tag);//参数代表标识

    void concle(String tag);

    void concleAll();
}
