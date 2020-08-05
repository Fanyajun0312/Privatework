package com.example.mymvplibrary.base;

import com.example.mymvplibrary.presenter.BasePresenter;
import com.example.mymvplibrary.view.BaseView;

/**
 * @date：2020/8/5
 * @describe：
 * @author：FanYaJun
 */
public abstract class BaseMvpActivity<V extends BaseView,P extends BasePresenter<V>>extends BaseActivity {

    public P mpresenter;

    @Override
    protected void inirEvent() {
        mpresenter = initPresenter();
        if (mpresenter != null) {
           mpresenter.attacthView((V) this);
        }
    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mpresenter!=null){
         mpresenter.destroyView();
         mpresenter=null;
        }
    }
}
