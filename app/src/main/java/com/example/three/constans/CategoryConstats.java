package com.example.three.constans;

import com.example.mymvplibrary.modle.BaseModel;
import com.example.mymvplibrary.modle.ModelBaseCallback;
import com.example.mymvplibrary.view.BaseView;
import com.example.three.bean.CategoryList;
import com.example.three.reqstbean.CategoryPrams;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

/**
 * @date：2020/8/10
 * @describe：商品的契约类 mvp调用继承接口
 * @author：FanYaJun
 */
public interface CategoryConstats {
    /**
     * view 实现接口 传输局 列表 one
     *   列表two
     */
    interface CaregoryView extends BaseView{
        void  showCantegoryTab(List<String> tabs);
        void  showCategoryList(List<CategoryList> categoryLists);
    }

    /**
     * 作为presenter的请求参  传参进p
     *
     */
    interface CaregoryPresenter{
        void getCategoryTab(CategoryPrams categoryPrams);
    }

    /**
     * tab 的请求参数传入P层传入  basemodecallback的数据 绑定生命周期LifecycleProvider
     *
     *
     */


    interface CategorModel extends BaseModel {
        void getCategoryTab(CategoryPrams categoryPrams, ModelBaseCallback<List<String>> modelBaseCallback, LifecycleProvider lifecycleProvider);
        void getCantegoryList(CategoryPrams categoryPrams,ModelBaseCallback<List<CategoryList>> modelBaseCallback,LifecycleProvider lifecycleProvider);
        void onError(String msg,int code);
    }


}
