package com.example.httplibary.utils;

import android.os.Looper;

/**
 * @date：2020/8/4
 * @describe：
 * @author：FanYaJun
 */
public class ThreadUtils {
    public static  final boolean isMainThreand(){
        return Looper.getMainLooper().getThread().getId()==Thread.currentThread().getId();
    }
}
