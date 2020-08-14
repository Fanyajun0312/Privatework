package com.example.mymvplibrary.base.adapter;

/**
 * @date：2020/8/14
 * @describe：
 * @author：FanYaJun
 */
public interface CommonType<DATA> {
    //根据布局类型的不同设置不同的viewype
    int getTypeLayoutId(int viewType);

    int getType(int position, DATA data);
}
