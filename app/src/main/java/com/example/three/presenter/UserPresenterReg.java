package com.example.three.presenter;

import com.example.mymvplibrary.modle.ModleFractory;
import com.example.mymvplibrary.presenter.BasePresenter;
import com.example.mymvplibrary.view.BaseView;
import com.example.three.bean.LoginBean;
import com.example.three.callBack.UserCallback;
import com.example.three.model.UserModelReg;
import com.example.three.view.UserViewReg;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * @date：2020/8/19
 * @describe：
 * @author：FanYaJun
 */
public class UserPresenterReg extends BasePresenter<UserViewReg> implements UserCallback {

    private UserModelReg userModelReg;

    public void getdata(String phone,String pwd,String code) {
        userModelReg = ModleFractory.creatModle(UserModelReg.class);
        userModelReg.getData(getLifeCycle() ,this,phone,pwd,code);
    }

    @Override
    public void onchenggong(LoginBean loginBean) {
        mView.onchenggong(loginBean);
    }

    @Override
    public void onError(String msg, int code) {
        mView.onError(msg, code);
    }

    @Override
    public void onCancle() {
        mView.onCancle();
    }
}
