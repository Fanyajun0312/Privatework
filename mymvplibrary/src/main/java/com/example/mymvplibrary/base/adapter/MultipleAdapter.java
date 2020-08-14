package com.example.mymvplibrary.base.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * @date：2020/8/14
 * @describe：
 * @author：FanYaJun
 */
public abstract class MultipleAdapter<DATA> extends BaseAdapter<DATA> {
    private CommonType<DATA> commonType;

    public MultipleAdapter(ArrayList<DATA> datas, Context context, int itemLayoutId, CommonType commonType) {
        super(datas, context, itemLayoutId);
        if (itemLayoutId == 0) {
            this.commonType = commonType;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return commonType.getType(position, datas.get(position));
    }


    @NonNull
    @Override
    public BaseViewHoalder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int typeLayoutId = commonType.getTypeLayoutId(viewType);
        return new BaseViewHoalder(layoutInflater.inflate(typeLayoutId, parent, false), context);
    }
}
