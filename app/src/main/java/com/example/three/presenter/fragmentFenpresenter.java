package com.example.three.presenter;


import com.example.mymvplibrary.modle.ModelBaseCallback;
import com.example.mymvplibrary.modle.ModleFractory;
import com.example.mymvplibrary.presenter.BasePresenter;
import com.example.three.constans.CategoryConstats;
import com.example.three.model.fragmentFenModel;
import com.example.three.reqstbean.CategoryPrams;

import java.util.List;

/**
 * @date：2020/8/5
 * @describe：
 * @author：FanYaJun
 */
public class fragmentFenpresenter extends BasePresenter<CategoryConstats.CaregoryView> implements CategoryConstats.CaregoryPresenter, ModelBaseCallback<List<String>> {

    @Override
    public void onSucess(List<String> strings) {
        if(mView!=null){
            mView.showCantegoryTab(strings);

        }
    }

    @Override
    public void OnError(String msg, int code) {
        mView.onError(msg, code);
    }

    @Override
    public void onCancle() {
        mView.onCancle();
    }

    @Override
    public void getCategoryTab(CategoryPrams categoryPrams) {
       ModleFractory.creatModle(fragmentFenModel.class)
               .getCategoryTab(categoryPrams,this,getLifeCycle());
    }
}
