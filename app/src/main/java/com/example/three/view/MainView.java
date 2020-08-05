package com.example.three.view;

import com.example.mymvplibrary.view.BaseView;
import com.example.three.Dian;
import com.example.three.postBean.oneBean;

import java.util.List;

/**
 * @date：2020/8/5
 * @describe：
 * @author：FanYaJun
 */
public interface MainView extends BaseView {
    void okbean(List<Dian> bean);
}
