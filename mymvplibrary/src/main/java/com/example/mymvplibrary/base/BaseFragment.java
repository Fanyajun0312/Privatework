package com.example.mymvplibrary.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.trello.rxlifecycle2.components.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @date：2020/8/5
 * @describe：
 * @author：FanYaJun
 */
public abstract class BaseFragment extends RxFragment {
    public Activity mActivity;
    private Context context;
    private View rootView;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(initLayoutId(),null);
        }
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEvent();
    }

    protected abstract void initEvent();
    protected abstract int initLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (rootView != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
        if(unbinder!=null){
            unbinder.unbind();
        }
    }
}
