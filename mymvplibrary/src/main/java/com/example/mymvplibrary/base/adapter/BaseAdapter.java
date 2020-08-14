package com.example.mymvplibrary.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymvplibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @date：2020/8/14
 * @describe：
 * @author：FanYaJun
 */
public abstract class BaseAdapter<DATA> extends RecyclerView.Adapter<BaseViewHoalder> {
    //存放数据的集合
    protected List<DATA> datas = new ArrayList<>();
    //上下文
    protected Context context;
    protected LayoutInflater layoutInflater;
    private int itemLayoutId;
    private ItemClickListener itemClickListener;

    public BaseAdapter(ArrayList<DATA> datas, Context context, int itemLayoutId) {
        this.datas = datas;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.itemLayoutId = itemLayoutId;
    }

    @NonNull
    @Override
    public BaseViewHoalder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = layoutInflater.inflate(itemLayoutId, parent, false);

        return new BaseViewHoalder(inflate, context);
    }

    //当前数据等于使用的数据刷新适配
    public void setNewData(@Nullable List<DATA> data) {
        this.datas = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHoalder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClickItem(position);
            }
        });

        bindData(holder, datas.get(position));
    }

    protected abstract void bindData(BaseViewHoalder holder, DATA data);


    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onClickItem(int postion);
    }
}
