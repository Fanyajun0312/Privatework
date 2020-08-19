package com.example.three.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mymvplibrary.base.BaseFragment;
import com.example.three.R;
import com.example.three.ui.UserloginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import jy.com.libumengsharelogin.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WodeFragment extends BaseFragment {

    @BindView(R.id.iv_name)
    ImageView ivName;
    @BindView(R.id.tv_reg_log)
    TextView tvRegLog;
    @BindView(R.id.re)
    RelativeLayout re;
    @BindView(R.id.tv_wait_pay)
    TextView tvWaitPay;
    @BindView(R.id.tv_wait_confirm)
    TextView tvWaitConfirm;
    @BindView(R.id.tv_order_completed)
    TextView tvOrderCompleted;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.vl)
    View vl;
    @BindView(R.id.iv_img_message)
    ImageView ivImgMessage;
    @BindView(R.id.iv_select)
    ImageView ivSelect;
    @BindView(R.id.ll_message)
    RelativeLayout llMessage;
    @BindView(R.id.vp_she)
    View vpShe;
    @BindView(R.id.iv_she)
    ImageView ivShe;
    @BindView(R.id.ll_she)
    RelativeLayout llShe;
    @BindView(R.id.ll_fen)
    RelativeLayout llFen;
    @BindView(R.id.iv_zhaun)
    ImageView ivZhaun;
    @BindView(R.id.ll_zhuan)
    RelativeLayout llZhuan;
    private View inflate;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        inflate = inflater.inflate(R.layout.fragment_wode, container, false);
//        ButterKnife.bind(this,inflate);
//        return inflate;
//    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_wode;
    }

    @Override
    protected void initData() {




        //点击进去登录页面
        tvRegLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserloginActivity.class));

            }
        });
    }


}
