package com.example.three.presenter;

import com.example.mymvplibrary.modle.ModleFractory;
import com.example.mymvplibrary.presenter.BasePresenter;
import com.example.three.bean.Dian;
import com.example.three.callBack.BackCall;
import com.example.three.model.MainModel;
import com.example.three.view.MainView;

import java.util.List;

/**
 * @date：2020/8/5
 * @describe：
 * @author：FanYaJun
 */
public class Mainpresenter extends BasePresenter<MainView> implements BackCall {

    public void getData() {
        MainModel model = ModleFractory.createModel(MainModel.class);
        model.getdata(this);
    }

    @Override
    public void okbena(List<Dian> oneBean) {
        mview.okbean(oneBean);
    }

    @Override
    public void onbean(String err) {
        mview.showToast(err);
    }
}
