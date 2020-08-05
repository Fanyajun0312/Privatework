package com.example.three;

import android.util.Log;

import com.example.mymvplibrary.base.BaseMvpActivity;
import com.example.three.postBean.oneBean;
import com.example.three.view.MainView;

import java.util.List;

public class MainActivity extends BaseMvpActivity<MainView,presenter> implements MainView {
    @Override
    protected int getLayoutView() {
        return R.layout.activity_main;
    }


    @Override
    protected presenter initPresenter() {
        return new presenter();
    }
    @Override
    protected void initData() {
        mpresenter.getData();
    }


    @Override
    protected void initListener() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void showToast(String err) {
        Log.i("TAG", "showToast: "+err);
    }

    @Override
    public void okbean(List<Dian> bean) {
        Log.i("TAG", "okbean: "+bean.toString());
    }
}
