package com.example.three.callBack;

import com.example.three.bean.Dian;

import java.util.List;

/**
 * @date：2020/8/5
 * @describe：
 * @author：FanYaJun
 */
public interface BackCall {
    void okbena(List<Dian> oneBean);
    void onbean(String err);
}
