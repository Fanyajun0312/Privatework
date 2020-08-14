package com.example.three.model;

import com.example.httplibary.clicent.HttpClient;
import com.example.httplibary.utils.JsonUtils;
import com.example.mymvplibrary.modle.ModelBaseCallback;
import com.example.three.app.HttpCallBack;
import com.example.three.bean.CategoryList;
import com.example.three.bean.CategoryTab;
import com.example.three.constans.CategoryConstats;
import com.example.three.reqstbean.CategoryPrams;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @date：2020/8/5
 * @describe：
 * @author：FanYaJun
 */
public class fragmentFenModel implements CategoryConstats.CategorModel {

    /**
     * 获取数据
     * @param categoryPrams 请求参数
     * @param modelBaseCallback//callback
     * @param lifecycleProvider//绑定生命周期
     */
    @Override
    public void getCategoryTab(CategoryPrams categoryPrams, ModelBaseCallback<List<String>> modelBaseCallback, LifecycleProvider lifecycleProvider) {
        new HttpClient.Builder().setApiUrl("category/getCategory")
                .setLifecycleProvider(lifecycleProvider)
                .setJsonbody(JsonUtils.classToJson(categoryPrams),true)
                .post()
                .build()
                .requset(new HttpCallBack<List<String>>() {
                    @Override
                    public void onError(String err, int onError) {
                        modelBaseCallback.OnError(err,onError);
                    }

                    @Override
                    public void cancle() {
                        modelBaseCallback.onCancle();
                    }

                    @Override
                    protected void onSuccess(List<String> pare) {
                        modelBaseCallback.onSucess(pare);
                    }

                    @Override
                    public List<String> convert(JsonElement result) {
                        List<CategoryTab> categoryTabs = JsonUtils.jsonToClassList(result, CategoryTab.class);
                        List<String> tabs=new ArrayList<>();
                        for (CategoryTab categoryTab : categoryTabs) {
                            tabs.add(categoryTab.getCategoryName());
                        }
                        return tabs;
                    }
                });
    }

    @Override
    public void getCantegoryList(CategoryPrams categoryPrams, ModelBaseCallback<List<CategoryList>> modelBaseCallback, LifecycleProvider lifecycleProvider) {

    }

    @Override
    public void onError(String msg, int code) {

    }
}
