package com.example.three.callBack;

import com.example.three.bean.LoginBean;

/**
 * @date：2020/8/19
 * @describe：
 * @author：FanYaJun
 */
public interface UserCallback {
    void onchenggong(LoginBean loginBean);
    void onError(String msg,int code);
    void  onCancle();
}
