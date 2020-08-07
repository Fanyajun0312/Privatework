package com.example.mymvplibrary.base;

import com.example.mymvplibrary.presenter.BasePresenter;
import com.example.mymvplibrary.view.BaseView;

/**
 * @date：2020/8/5
 * @describe：
 * @author：FanYaJun
 */
public abstract class BaseMvpFragment<V extends BaseView,P extends BasePresenter<V>> extends BaseFragment {
        public P mPresneter;
    @Override
    protected void initEvent() {
        mPresneter=initPresenter();
        if(mPresneter!=null){
         mPresneter.attacthView((V) this);
        }

    }

    protected abstract P initPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPresneter!=null){
            mPresneter.destroyView();
            mPresneter=null;
        }
    }
}
