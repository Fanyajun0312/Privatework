package com.example.three.adapter;

import android.content.Context;

import com.example.mymvplibrary.base.adapter.BaseAdapter;
import com.example.mymvplibrary.base.adapter.BaseViewHoalder;
import com.example.three.R;

import java.util.ArrayList;

/**
 * @date：2020/8/14
 * @describe：
 * @author：FanYaJun
 */
public class RlvAdapterTwo extends BaseAdapter<String> {
    public RlvAdapterTwo(ArrayList<String> datas, Context context, int itemLayoutId) {
        super(datas, context, R.layout.fixed_bottom_navigation_item);
    }

    @Override
    protected void bindData(BaseViewHoalder holder, String s) {

    }
}
