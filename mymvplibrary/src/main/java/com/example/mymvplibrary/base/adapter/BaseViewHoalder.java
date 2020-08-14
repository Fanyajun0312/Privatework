package com.example.mymvplibrary.base.adapter;

import android.content.Context;
import android.content.IntentFilter;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymvplibrary.utils.ImageLoad;
import com.example.mymvplibrary.view.BaseView;

/**
 * @date：2020/8/14
 * @describe：
 * @author：FanYaJun
 */
public class BaseViewHoalder extends RecyclerView.ViewHolder {
    private View view;
    private Context context;
    private SparseArray sparseArray = new SparseArray<>();//创建集合管理所有布局上的id

    public BaseViewHoalder(@NonNull View itemView, Context context) {
        super(itemView);
        view = itemView;
        this.context = context;
    }

    //获取不布局的id 调用findviewbyid方法查找view布局的id  放入sparseArray集合 key值必须是int类型
    public <T extends View> T getView(int viewId) {
        if (sparseArray.get(viewId) == null) {
            View viewById = view.findViewById(viewId);
            sparseArray.put(viewId, viewById);

        }
        return (T) sparseArray.get(viewId);
    }

    /**
     * 附带点击事件的text
     *
     * @param textViewId          textview的id
     * @param text                赋值
     * @param onItemClickListener 点击事件
     * @return
     */
    public BaseViewHoalder setTextViewContent(int textViewId, final String text, final OnItemClickListener onItemClickListener) {
        TextView view = getView(textViewId);
        if (onItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(text);
                }
            });
        }
        view.setText(text);
        return this;
    }

    public BaseViewHoalder setTvContent(int textViewId, final String text) {
        TextView textView = getView(textViewId);
        textView.setText(text);
        return this;
    }

    public BaseViewHoalder setIvContent(int textViewId, final String path, final OnItemClickListener onItemClickListener) {
        ImageView imageView = getView(textViewId);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(path);
            }
        });
        ImageLoad imageLoad = new ImageLoad(context);
        imageLoad.loadImage(imageView, path);
        return this;
    }

    public BaseViewHoalder setIvContent(int textViewId, final String path) {
        ImageView imageView = getView(textViewId);
        ImageLoad imageLoad = new ImageLoad(context);
        imageLoad.loadImage(imageView, path);
        return this;
    }

    /**
     * i定义点击事件的接口
     */
    interface OnItemClickListener {
        void onItemClick(String itemString);
    }
}
