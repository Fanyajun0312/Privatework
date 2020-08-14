package com.example.mymvplibrary.base;

import com.example.mymvplibrary.presenter.BasePresenter;
import com.example.mymvplibrary.view.BaseView;

/**
 * 项目名：Shopping
 * 包名：  com.example.mvplibrary.base
 * 文件名：BaseMvpFragment
 * 创建者：liangxq
 * 创建时间：2020/8/5  15:39
 * 描述：TODO
 */
public abstract class BaseMvpFragment<V extends BaseView,P extends BasePresenter<V>> extends BaseFragment{
    public P mPresenter;
    @Override
    protected void initData() {
        mPresenter=initPresenter();
        if(mPresenter!=null){
            mPresenter.attacthView((V) this);
            initEventAndData();
        }

    }

    protected abstract void initEventAndData();

    protected abstract P initPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresenter!=null){
            mPresenter.destoryView();
            mPresenter=null;
        }
    }
}
