package com.example.mymvplibrary.modle;

/**
 * 项目名：Shopping
 * 包名：  com.example.mvplibrary.model
 * 文件名：ModelBaseCallback
 * 创建者：liangxq
 * 创建时间：2020/8/6  10:52
 * 描述：TODO
 */
public interface ModelBaseCallback<T> {
    void onSucess(T t);
    void OnError(String msg, int code);
    void onCancle();
}
